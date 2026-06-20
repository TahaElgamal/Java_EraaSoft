<%@page import="com.item.model.Item"%>
<%@ page import="com.item.model.User" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Show Items</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        /* ========== CSS Variables ========== */
        :root {
            --primary-color: #2563eb;
            --primary-hover: #1d4ed8;
            --danger-color: #dc2626;
            --danger-hover: #b91c1c;
            --success-color: #16a34a;
            --success-hover: #15803d;
            --details-color: #7c3aed;
            --details-hover: #6d28d9;
            --bg-color: #f1f5f9;
            --card-bg: #ffffff;
            --text-primary: #1e293b;
            --text-secondary: #64748b;
            --border-color: #e2e8f0;
            --shadow-sm: 0 1px 2px 0 rgb(0 0 0 / 0.05);
            --shadow-md: 0 4px 6px -1px rgb(0 0 0 / 0.1), 0 2px 4px -2px rgb(0 0 0 / 0.1);
            --shadow-lg: 0 10px 15px -3px rgb(0 0 0 / 0.1), 0 4px 6px -4px rgb(0 0 0 / 0.1);
            --radius: 8px;
            --transition: all 0.2s ease-in-out;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', system-ui, -apple-system, sans-serif;
            background: linear-gradient(135deg, #f1f5f9 0%, #e2e8f0 100%);
            color: var(--text-primary);
            min-height: 100vh;
            line-height: 1.6;
        }

        /* ========== Top Navigation Bar ========== */
        .navbar {
            background: linear-gradient(135deg, var(--card-bg) 0%, #f8fafc 100%);
            border-bottom: 1px solid var(--border-color);
            box-shadow: var(--shadow-md);
            position: sticky;
            top: 0;
            z-index: 1000;
            backdrop-filter: blur(10px);
        }

        .navbar-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 0 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            height: 70px;
        }

        /* ========== Logo / Brand ========== */
        .navbar-brand {
            display: flex;
            align-items: center;
            gap: 12px;
            text-decoration: none;
        }

        .brand-icon {
            width: 40px;
            height: 40px;
            background: linear-gradient(135deg, var(--primary-color) 0%, #3b82f6 100%);
            border-radius: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-size: 1.25rem;
            box-shadow: 0 4px 12px rgba(37, 99, 235, 0.3);
        }

        .brand-text {
            font-size: 1.25rem;
            font-weight: 700;
            color: var(--text-primary);
            letter-spacing: -0.5px;
        }

        /* ========== Navbar Right Section ========== */
        .navbar-right {
            display: flex;
            align-items: center;
            gap: 20px;
        }

        /* ========== User Profile Dropdown ========== */
        .user-dropdown {
            position: relative;
        }

        .user-trigger {
            display: flex;
            align-items: center;
            gap: 12px;
            padding: 8px 16px;
            background: #f8fafc;
            border: 1px solid var(--border-color);
            border-radius: 50px;
            cursor: pointer;
            transition: var(--transition);
        }

        .user-trigger:hover {
            background: #eff6ff;
            border-color: #bfdbfe;
            box-shadow: var(--shadow-sm);
        }

        .user-avatar-nav {
            width: 36px;
            height: 36px;
            background: linear-gradient(135deg, var(--primary-color) 0%, #60a5fa 100%);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-size: 0.875rem;
            box-shadow: 0 2px 8px rgba(37, 99, 235, 0.3);
        }

        .user-details {
            display: flex;
            flex-direction: column;
            line-height: 1.3;
        }

        .user-name-nav {
            font-weight: 600;
            font-size: 0.875rem;
            color: var(--text-primary);
        }

        .user-role-nav {
            font-size: 0.75rem;
            color: var(--text-secondary);
            font-weight: 500;
        }

        .dropdown-chevron {
            color: var(--text-secondary);
            font-size: 0.75rem;
            transition: var(--transition);
        }

        .user-dropdown:hover .dropdown-chevron {
            transform: rotate(180deg);
        }

        /* Dropdown Menu */
        .dropdown-menu {
            position: absolute;
            top: calc(100% + 10px);
            right: 0;
            background: var(--card-bg);
            border: 1px solid var(--border-color);
            border-radius: var(--radius);
            box-shadow: var(--shadow-lg);
            min-width: 220px;
            opacity: 0;
            visibility: hidden;
            transform: translateY(-10px);
            transition: var(--transition);
            overflow: hidden;
        }

        .user-dropdown:hover .dropdown-menu {
            opacity: 1;
            visibility: visible;
            transform: translateY(0);
        }

        .dropdown-header {
            padding: 16px 20px;
            background: linear-gradient(135deg, #f8fafc 0%, #f1f5f9 100%);
            border-bottom: 1px solid var(--border-color);
        }

        .dropdown-header-text {
            font-weight: 600;
            font-size: 0.9375rem;
            color: var(--text-primary);
        }

        .dropdown-header-email {
            font-size: 0.8125rem;
            color: var(--text-secondary);
            margin-top: 2px;
        }

        .dropdown-item {
            display: flex;
            align-items: center;
            gap: 12px;
            padding: 12px 20px;
            color: var(--text-primary);
            text-decoration: none;
            font-size: 0.875rem;
            font-weight: 500;
            transition: var(--transition);
        }

        .dropdown-item:hover {
            background: #f8fafc;
            color: var(--primary-color);
        }

        .dropdown-item i {
            width: 20px;
            text-align: center;
            color: var(--text-secondary);
            font-size: 0.9375rem;
        }

        .dropdown-item:hover i {
            color: var(--primary-color);
        }

        .dropdown-divider {
            height: 1px;
            background: var(--border-color);
            margin: 4px 16px;
        }

        /* ========== Logout Button ========== */
        .logout-btn-nav {
            display: inline-flex;
            align-items: center;
            gap: 8px;
            padding: 10px 20px;
            background: linear-gradient(135deg, #fef2f2 0%, #fee2e2 100%);
            color: var(--danger-color);
            border: 1.5px solid #fecaca;
            border-radius: 50px;
            font-size: 0.875rem;
            font-weight: 600;
            text-decoration: none;
            cursor: pointer;
            transition: var(--transition);
        }

        .logout-btn-nav:hover {
            background: linear-gradient(135deg, var(--danger-color) 0%, #ef4444 100%);
            color: white;
            transform: translateY(-1px);
            box-shadow: 0 4px 12px rgba(220, 38, 38, 0.3);
            border-color: var(--danger-color);
        }

        .logout-btn-nav i {
            font-size: 0.9375rem;
        }

        /* ========== Main Container ========== */
        .layer {
            max-width: 1200px;
            margin: 40px auto;
            padding: 0 20px;
        }

        /* ========== Header ========== */
        h1 {
            text-align: center;
            color: var(--text-primary);
            font-size: 2.25rem;
            font-weight: 700;
            margin-bottom: 30px;
            position: relative;
            padding-bottom: 15px;
        }

        h1::after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 50%;
            transform: translateX(-50%);
            width: 60px;
            height: 4px;
            background: linear-gradient(90deg, var(--primary-color), #60a5fa);
            border-radius: 2px;
        }

        /* ========== Table Container ========== */
        table {
            width: 100%;
            background: var(--card-bg);
            border-collapse: separate;
            border-spacing: 0;
            border-radius: var(--radius);
            box-shadow: var(--shadow-lg);
            overflow: hidden;
            margin-bottom: 30px;
        }

        /* ========== Table Header ========== */
        thead {
            background: linear-gradient(135deg, var(--primary-color) 0%, #3b82f6 100%);
            color: white;
        }

        thead th {
            padding: 16px 20px;
            text-align: left;
            font-weight: 600;
            font-size: 0.875rem;
            text-transform: uppercase;
            letter-spacing: 0.05em;
            border: none;
        }

        thead th:first-child {
            border-top-left-radius: var(--radius);
        }

        thead th:last-child {
            border-top-right-radius: var(--radius);
        }

        /* ========== Table Body ========== */
        tbody tr {
            transition: var(--transition);
            border-bottom: 1px solid var(--border-color);
        }

        tbody tr:last-child {
            border-bottom: none;
        }

        tbody tr:hover {
            background-color: #f8fafc;
            transform: scale(1.002);
            box-shadow: var(--shadow-sm);
        }

        tbody td {
            padding: 16px 20px;
            color: var(--text-primary);
            font-size: 0.9375rem;
            border-bottom: 1px solid var(--border-color);
        }

        tbody tr:last-child td:first-child {
            border-bottom-left-radius: var(--radius);
        }

        tbody tr:last-child td:last-child {
            border-bottom-right-radius: var(--radius);
        }

        /* ID Column Styling */
        tbody td:first-child strong {
            color: var(--primary-color);
            font-weight: 700;
            font-size: 1rem;
            background: #eff6ff;
            padding: 4px 12px;
            border-radius: 20px;
            display: inline-block;
        }

        /* Price Column */
        tbody td:nth-child(3) {
            font-weight: 600;
            color: var(--success-color);
        }

        /* Total Number Column */
        tbody td:nth-child(4) {
            font-weight: 500;
        }

        /* ========== Action Buttons ========== */
        tbody td:last-child {
            display: flex;
            gap: 10px;
            align-items: center;
        }

        tbody td:last-child a {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            padding: 8px 16px;
            border-radius: 6px;
            font-size: 0.875rem;
            font-weight: 500;
            text-decoration: none;
            cursor: pointer;
            transition: var(--transition);
            border: none;
        }

        /* Update Button */
        tbody td:last-child a:first-child {
            background-color: #eff6ff;
            color: var(--primary-color);
            border: 1px solid #bfdbfe;
        }

        tbody td:last-child a:first-child:hover {
            background-color: var(--primary-color);
            color: white;
            transform: translateY(-1px);
            box-shadow: 0 4px 12px rgba(37, 99, 235, 0.3);
        }

        /* Delete Button */
        tbody td:last-child a:nth-child(2) {
            background-color: #fef2f2;
            color: var(--danger-color);
            border: 1px solid #fecaca;
        }

        tbody td:last-child a:nth-child(2):hover {
            background-color: var(--danger-color);
            color: white;
            transform: translateY(-1px);
            box-shadow: 0 4px 12px rgba(220, 38, 38, 0.3);
        }

        /* Add Details Button (NEW) */
        tbody td:last-child a:last-child {
            background-color: #f5f3ff;
            color: var(--details-color);
            border: 1px solid #ddd6fe;
        }

        tbody td:last-child a:last-child:hover {
            background-color: var(--details-color);
            color: white;
            transform: translateY(-1px);
            box-shadow: 0 4px 12px rgba(124, 58, 237, 0.3);
        }

        /* ========== Add Item Button ========== */
        button.f {
            display: block;
            margin: 0 auto;
            background: linear-gradient(135deg, var(--success-color) 0%, #22c55e 100%);
            border: none;
            padding: 0;
            border-radius: var(--radius);
            box-shadow: var(--shadow-md);
            transition: var(--transition);
            cursor: pointer;
        }

        button.f:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(22, 163, 74, 0.4);
        }

        button.f a {
            display: inline-block;
            padding: 14px 32px;
            color: white;
            text-decoration: none;
            font-weight: 600;
            font-size: 1rem;
            border-radius: var(--radius);
        }

        /* ========== Empty State ========== */
        tbody:empty::after {
            content: 'No items found';
            display: block;
            text-align: center;
            padding: 40px;
            color: var(--text-secondary);
            font-size: 1rem;
        }

        /* ========== Responsive Design ========== */
        @media screen and (max-width: 768px) {
            .navbar-container {
                padding: 0 15px;
                height: 60px;
            }

            .brand-text {
                display: none;
            }

            .user-details {
                display: none;
            }

            .dropdown-chevron {
                display: none;
            }

            .layer {
                margin: 20px auto;
                padding: 0 10px;
            }

            h1 {
                font-size: 1.75rem;
            }

            table {
                display: block;
                overflow-x: auto;
                white-space: nowrap;
            }

            thead th, tbody td {
                padding: 12px 16px;
                font-size: 0.875rem;
            }

            tbody td:last-child {
                flex-direction: column;
                gap: 6px;
            }

            tbody td:last-child a {
                width: 100%;
                padding: 6px 12px;
            }

            button.f a {
                padding: 12px 24px;
                font-size: 0.9375rem;
            }
        }

        @media screen and (max-width: 480px) {
            .logout-btn-nav span {
                display: none;
            }

            .logout-btn-nav {
                padding: 10px 14px;
            }

            h1 {
                font-size: 1.5rem;
            }

            thead th {
                font-size: 0.75rem;
                padding: 10px 12px;
            }

            tbody td {
                padding: 10px 12px;
                font-size: 0.8125rem;
            }
        }

        /* ========== Animations ========== */
        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        tbody tr {
            animation: fadeIn 0.3s ease-out forwards;
        }

        tbody tr:nth-child(1) { animation-delay: 0.05s; }
        tbody tr:nth-child(2) { animation-delay: 0.1s; }
        tbody tr:nth-child(3) { animation-delay: 0.15s; }
        tbody tr:nth-child(4) { animation-delay: 0.2s; }
        tbody tr:nth-child(5) { animation-delay: 0.25s; }

        /* ========== Scrollbar Styling ========== */
        table::-webkit-scrollbar {
            height: 8px;
        }

        table::-webkit-scrollbar-track {
            background: var(--border-color);
            border-radius: 4px;
        }

        table::-webkit-scrollbar-thumb {
            background: var(--primary-color);
            border-radius: 4px;
        }

        table::-webkit-scrollbar-thumb:hover {
            background: var(--primary-hover);
        }
    </style>
</head>
<body>

    <!-- Professional Top Navigation Bar -->
    <nav class="navbar">
        <div class="navbar-container">
            <!-- Brand / Logo -->
            <a href="/item-service-mvc-project/itemController" class="navbar-brand">
                <div class="brand-icon">
                    <i class="fas fa-boxes-stacked"></i>
                </div>
                <span class="brand-text">Taha Elgamal STORE</span>
            </a>

            <!-- Right Section -->
            <div class="navbar-right">
                <!-- User Profile Dropdown -->
                <div class="user-dropdown">
                    <div class="user-trigger">
                        <div class="user-avatar-nav">
                            <i class="fas fa-user"></i>
                        </div>
                        <div class="user-details">
                        	
                            <span class="user-name-nav">${User.userName }</span>
                        </div>
                        <i class="fas fa-chevron-down dropdown-chevron"></i>
                    </div>

                    <!-- Dropdown Menu -->
                    <div class="dropdown-menu">
                        <div class="dropdown-header">
                            <div class="dropdown-header-text">${User.userName }</div>
                            <div class="dropdown-header-email">${User.email }</div>
                        </div>
                        <a href="#" class="dropdown-item">
                            <i class="fas fa-user-circle"></i>
                            Profile
                        </a>
                        <a href="#" class="dropdown-item">
                            <i class="fas fa-cog"></i>
                            Settings
                        </a>
                        <div class="dropdown-divider"></div>
                        <a href="/item-service-mvc-project/LoginController?action=deleteUser" class="dropdown-item" style="color: var(--danger-color);">
                            <i class="fas fa-sign-out-alt" style="color: var(--danger-color);"></i>
                            Delete Account
                        </a>
                    </div>
                </div>

                <!-- Logout Button -->
                <a href="/item-service-mvc-project/LoginController?action=logOut" class="logout-btn-nav">
                    <i class="fas fa-sign-out-alt"></i>
                    <span>Logout</span>
                </a>
            </div>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="layer">
        <table>
            <h1>Items</h1>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>PRICE</th>
                    <th>TOTAL_NUMBER</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Item> items = (List<Item>)request.getAttribute("allItems");
                    for(Item item : items){
                %>
                <tr>
                    <td><strong><%=item.getId()%></strong></td>
                    <td><%=item.getName()%></td>
                    <td><%=item.getPrice()%></td>
                    <td><%=item.getTotalNumber()%></td>
                    <td>
                        <a href="/item-service-mvc-project/itemController?action=show-item&id=<%=item.getId()%>">Update</a>
                        <a href="/item-service-mvc-project/itemController?action=delete-item&id=<%=item.getId()%>">Delete</a>
	                        <% if(!item.isHasDetails()){%>
								<a href="add-details.jsp?id=<%=item.getId()%>">Add Details</a>
							
							<%}else{ %>
                        		<a href="/item-service-mvc-project/itemController?action=show-details&id=<%=item.getId()%>">Update Details</a>
                        <%} %>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>

        <button class="f"><a href="add-item.jsp">Add Item</a></button>
    </div>

</body>
</html>