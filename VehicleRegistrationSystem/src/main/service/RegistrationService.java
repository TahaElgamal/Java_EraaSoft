package main.service;

import main.exception.DuplicatePlateException;
import main.exception.VehicleNotFoundException;
import main.model.Vehicle;

import java.util.*;
import java.util.stream.Collectors;
import java.util.IntSummaryStatistics;

/**
 * Service class responsible for managing vehicle registrations.
 * Provides CRUD operations, filtering, sorting, and statistical analysis
 * for vehicles in the registration system.
 *
 * @author Your Name
 * @version 1.0
 */
public class RegistrationService {

    /* Master list containing all registered vehicles */
    private List<Vehicle> vehicleList = new ArrayList<>();

    /* Index for fast lookup of vehicles by their plate number */
    private Map<String, Vehicle> plateIndex = new HashMap<>();

    /* Set to track all registered plate numbers for duplicate detection */
    private Set<String> registeredPlates = new HashSet<>();

    /**
     * Registers a new vehicle in the system.
     * Validates that the plate number is not already registered.
     *
     * @param vehicle the vehicle to be registered
     * @throws DuplicatePlateException if the plate number is already registered
     */
    public void registerVehicle(Vehicle vehicle) {
        String plate = normalize(vehicle.getPlateNumber());

        if (registeredPlates.contains(plate)) {
            throw new DuplicatePlateException(plate);
        }
        registeredPlates.add(plate);
        plateIndex.put(plate, vehicle);
        vehicleList.add(vehicle);
    }

    /**
     * Finds a vehicle by its plate number.
     *
     * @param plate the plate number to search for
     * @return the Vehicle object with the specified plate number
     * @throws VehicleNotFoundException if no vehicle is found with the given plate
     */
    public Vehicle findByPlate(String plate) {
        String normalizedPlate = normalize(plate);
        if (!plateIndex.containsKey(normalizedPlate)) {
            throw new VehicleNotFoundException(normalizedPlate);
        }
        return plateIndex.get(normalizedPlate);
    }

    /**
     * Deletes a vehicle from the system by its plate number.
     * Removes the vehicle from all internal data structures.
     *
     * @param plate the plate number of the vehicle to delete
     * @throws VehicleNotFoundException if no vehicle is found with the given plate
     */
    public void deleteVehicle(String plate) {
        String normalizedPlate = normalize(plate);
        Vehicle vehicleSelected = findByPlate(normalizedPlate);

        plateIndex.remove(normalizedPlate);
        registeredPlates.remove(normalizedPlate);
        vehicleList.remove(vehicleSelected);
    }

    /**
     * Updates the owner name of a vehicle.
     *
     * @param plate    the plate number of the vehicle to update
     * @param newOwner the new owner name to set
     * @throws VehicleNotFoundException if no vehicle is found with the given plate
     */
    public void updateOwner(String plate, String newOwner) {
        Vehicle vehicleSelected = findByPlate(plate);
        vehicleSelected.setOwnerName(newOwner);
    }

    /**
     * Returns an unmodifiable list of all registered vehicles.
     *
     * @return an unmodifiable List containing all vehicles
     */
    public List<Vehicle> getAllVehicles() {
        return Collections.unmodifiableList(vehicleList);
    }

    /**
     * Normalizes a plate number by converting it to uppercase.
     *
     * @param plate the plate number to normalize
     * @return the normalized plate number in uppercase
     */
    private String normalize(String plate) {
        return plate.toUpperCase();
    }

    /**
     * Filters vehicles by their type.
     *
     * @param type the vehicle type to filter by (e.g., "Car", "Motorcycle", "Truck")
     * @return a List of vehicles matching the specified type
     */
    public List<Vehicle> filterByType(String type) {
        return vehicleList.stream()
                .filter(v -> v.getVehicleType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }

    /**
     * Searches for vehicles by owner name.
     * Performs a case-insensitive partial match on the owner name.
     *
     * @param ownerName the owner name to search for
     * @return a List of vehicles whose owner name contains the search string
     */
    public List<Vehicle> getVehiclesByOwner(String ownerName) {
        return vehicleList.stream()
                .filter(v -> v.getOwnerName().toLowerCase()
                        .contains(ownerName.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves vehicles with expired registrations.
     * A registration is considered expired if it's older than 5 years.
     * Results are sorted by registration year in ascending order.
     *
     * @param currentYear the current year to compare against
     * @return a sorted List of vehicles with expired registrations
     */
    public List<Vehicle> getExpiredRegistrations(int currentYear) {
        return vehicleList.stream()
                .filter(v -> (currentYear - v.getRegistrationYear()) > 5)
                .sorted(Comparator.comparingInt(Vehicle::getRegistrationYear))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all vehicles sorted by registration year.
     *
     * @param ascending if true, sorts ascending (oldest first);
     *                  if false, sorts descending (newest first)
     * @return a sorted List of vehicles
     */
    public List<Vehicle> getSortedByYear(boolean ascending) {
        Comparator<Vehicle> comp = Comparator.comparingInt(Vehicle::getRegistrationYear);
        if (!ascending) {
            comp = comp.reversed();
        }
        return vehicleList.stream().sorted(comp).collect(Collectors.toList());
    }

    /**
     * Prints comprehensive statistics about all registered vehicles.
     * Statistics include:
     * - Total vehicle count
     * - Average registration year
     * - Newest and oldest vehicle years
     * - Distribution by vehicle type
     * - Count of active vs expired vehicles
     */
    public void printStatistics() {
        IntSummaryStatistics stats = vehicleList.stream()
                .mapToInt(Vehicle::getRegistrationYear)
                .summaryStatistics();

        Map<String, Long> byType = vehicleList.stream()
                .collect(Collectors.groupingBy(
                        Vehicle::getVehicleType,
                        Collectors.counting()
                ));

        Map<Boolean, Long> byStatus = vehicleList.stream()
                .collect(Collectors.partitioningBy(
                        v -> v.getStatus().equalsIgnoreCase("ACTIVE"),
                        Collectors.counting()
                ));

        System.out.println("\n========== REGISTRATION STATISTICS ==========");
        System.out.printf("Total Vehicles : %d%n", stats.getCount());
        System.out.printf("Average Year   : %.0f%n", stats.getAverage());
        System.out.printf("Newest Vehicle : %d%n", stats.getMax());
        System.out.printf("Oldest Vehicle : %d%n", stats.getMin());
        System.out.println("----------------------------------------------");
        System.out.println("Vehicles by Type:");
        byType.forEach((type, count) ->
                System.out.printf("%s : %d%n", type, count)
        );
        System.out.println("----------------------------------------------");
        System.out.printf("ACTIVE vehicles : %d%n", byStatus.get(true));
        System.out.printf("EXPIRED vehicles: %d%n", byStatus.get(false));
        System.out.println("==============================================");
    }
}