package main.service;

import main.exception.DuplicatePlateException;
import main.exception.VehicleNotFoundException;
import main.model.Vehicle;

import java.util.*;
import java.util.stream.Collectors;
import java.util.IntSummaryStatistics;

public class RegistrationService {

    private List<Vehicle> vehicleList = new ArrayList<>();
    private Map<String, Vehicle> plateIndex = new HashMap<>();
    private Set<String> registeredPlates = new HashSet<>();

    public void registerVehicle (Vehicle vehicle){
        String plate =normalize(vehicle.getPlateNumber());

        if (registeredPlates.contains(plate)){
            throw new DuplicatePlateException(plate);
        }
        registeredPlates.add(plate);
        plateIndex.put(plate,vehicle);
        vehicleList.add(vehicle);
    }

    public Vehicle findByPlate (String plate){

        String normalizedPlate=normalize(plate);
        if (!plateIndex.containsKey(normalizedPlate)){
            throw new VehicleNotFoundException(normalizedPlate);
        }
        return plateIndex.get(normalizedPlate);
    }

    public void deleteVehicle (String plate){
        String normalizedPlate = normalize(plate);
        Vehicle vehicleSelected =findByPlate(normalizedPlate);

        plateIndex.remove(normalizedPlate);
        registeredPlates.remove(normalizedPlate);
        vehicleList.remove(vehicleSelected);
    }

    public void updateOwner(String plate, String newOwner){
        Vehicle vehicleSelected =findByPlate(plate);
        vehicleSelected.setOwnerName(newOwner);
    }

    public List<Vehicle> getAllVehicles(){

        return Collections.unmodifiableList(vehicleList);
    }


    private String normalize (String plate){
        return plate.toUpperCase();
    }

    public List<Vehicle> filterByType(String type) {

        return vehicleList.stream().filter(v ->
                        v.getVehicleType()
                                .equalsIgnoreCase(type)
                ).collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByOwner(String ownerName) {

        return vehicleList.stream()

                .filter(v ->
                        v.getOwnerName()
                                .toLowerCase()
                                .contains(ownerName.toLowerCase())
                ).collect(Collectors.toList());
    }

    public List<Vehicle> getExpiredRegistrations(int currentYear) {

        return vehicleList.stream()

                .filter(v ->
                        (currentYear - v.getRegistrationYear()) > 5
                ).sorted(
                        Comparator.comparingInt(
                                Vehicle::getRegistrationYear
                        )
                ).collect(Collectors.toList());
    }

    public List<Vehicle> getSortedByYear(boolean ascending) {

        Comparator<Vehicle> comp =
                Comparator.comparingInt(
                        Vehicle::getRegistrationYear
                );
        if (!ascending) {
            comp = comp.reversed();
        }

        return vehicleList.stream().sorted(comp).collect(Collectors.toList());
    }

    public void printStatistics() {

        IntSummaryStatistics stats = vehicleList.stream()

                .mapToInt(Vehicle::getRegistrationYear)

                .summaryStatistics();

        Map<String, Long> byType = vehicleList.stream()

                .collect(
                        Collectors.groupingBy(
                                Vehicle::getVehicleType,
                                Collectors.counting()
                        )
                );

        Map<Boolean, Long> byStatus = vehicleList.stream()

                .collect(
                        Collectors.partitioningBy(
                                v -> v.getStatus()
                                        .equalsIgnoreCase("ACTIVE"),

                                Collectors.counting()
                        )
                );

        System.out.println("\n========== REGISTRATION STATISTICS ==========");

        System.out.printf("Total Vehicles : %d%n",
                stats.getCount());

        System.out.printf("Average Year   : %.0f%n",
                stats.getAverage());

        System.out.printf("Newest Vehicle : %d%n",
                stats.getMax());

        System.out.printf("Oldest Vehicle : %d%n",
                stats.getMin());

        System.out.println("----------------------------------------------");

        System.out.println("Vehicles by Type:");

        byType.forEach((type, count) ->
                System.out.printf("%s : %d%n", type, count)
        );

        System.out.println("----------------------------------------------");

        System.out.printf("ACTIVE vehicles : %d%n",
                byStatus.get(true));

        System.out.printf("EXPIRED vehicles: %d%n",
                byStatus.get(false));

        System.out.println("==============================================");
    }
}
