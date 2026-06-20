<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ page import="java.lang.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login & Sign Up</title>
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        :root {
            --primary-color: #2563eb;
            --primary-hover: #1d4ed8;
            --success-color: #16a34a;
            --error-color: #dc2626;
            --error-bg: #fef2f2;
            --error-border: #fecaca;
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
            background: linear-gradient(135deg, #1e3a8a 0%, #2563eb 50%, #3b82f6 100%);
            color: var(--text-primary);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: flex-end;
            padding: 40px 60px;
            line-height: 1.6;
            position: relative;
            overflow: hidden;
        }

        /* ========== FLOATING BACKGROUND ICONS ========== */
        .bg-icon {
            position: fixed;
            top: 50%;
            left: 30%;
            transform: translate(-50%, -50%);
            font-size: 30rem;
            color: rgba(255, 255, 255, 0.06);
            pointer-events: none;
            z-index: 0;
            animation: floatSlow 6s ease-in-out infinite;
        }

        .bg-icon-2 {
            position: fixed;
            top: 20%;
            left: 15%;
            font-size: 8rem;
            color: rgba(255, 255, 255, 0.04);
            pointer-events: none;
            z-index: 0;
            animation: floatSlow 8s ease-in-out infinite reverse;
        }

        .bg-icon-3 {
            position: fixed;
            bottom: 15%;
            left: 20%;
            font-size: 6rem;
            color: rgba(255, 255, 255, 0.05);
            pointer-events: none;
            z-index: 0;
            animation: floatSlow 7s ease-in-out infinite;
        }

        /* ========== STORE BRANDING ========== */
        .store-brand {
            position: fixed;
            top: 50%;
            left: 30%;
            transform: translate(-50%, -50%);
            text-align: center;
            z-index: 1;
            pointer-events: none;
        }

        .store-icon {
            font-size: 4rem;
            color: rgba(255, 255, 255, 0.95);
            margin-bottom: 15px;
            filter: drop-shadow(0 4px 15px rgba(0,0,0,0.2));
        }

        .store-name {
            font-size: 2.2rem;
            font-weight: 700;
            color: white;
            letter-spacing: -1px;
            text-shadow: 0 2px 10px rgba(0,0,0,0.2);
        }

        .store-tagline {
            font-size: 0.95rem;
            color: rgba(255, 255, 255, 0.8);
            margin-top: 8px;
            font-weight: 300;
        }

        /* ========== MAIN CARD (Right Side) WITH PROFESSIONAL ANIMATION ========== */
        .login-wrap {
            position: relative;
            z-index: 1;
            max-width: 450px;
            width: 100%;
            max-height: 90vh;
            background: var(--card-bg);
            border-radius: var(--radius);
            box-shadow: 0 25px 50px -12px rgba(0, 0, 0, 0.35);
            overflow: hidden;
            display: flex;
            flex-direction: column;
            
            /* Professional entrance animation */
            opacity: 0;
            transform: translateX(60px) scale(0.95);
            animation: cardEnter 0.8s cubic-bezier(0.34, 1.56, 0.64, 1) 0.2s forwards;
        }

        /* Top gradient line with shimmer */
        .login-wrap::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 4px;
            background: linear-gradient(90deg, var(--primary-color), #60a5fa, var(--primary-color));
            background-size: 200% 100%;
            z-index: 10;
            animation: shimmer 2s ease-in-out infinite;
        }

        .login-html {
            padding: 50px 40px;
            overflow-y: auto;
            flex: 1;
            
            /* Subtle fade in for content */
            opacity: 0;
            animation: contentFade 0.5s ease 0.6s forwards;
        }

        /* Custom Scrollbar */
        .login-html::-webkit-scrollbar {
            width: 6px;
        }

        .login-html::-webkit-scrollbar-track {
            background: transparent;
        }

        .login-html::-webkit-scrollbar-thumb {
            background: var(--border-color);
            border-radius: 3px;
        }

        .login-html::-webkit-scrollbar-thumb:hover {
            background: var(--primary-color);
        }

        /* ========== Tab Navigation ========== */
        .login-html input[type="radio"] {
            display: none;
        }

        .tab {
            display: inline-block;
            padding: 0 15px 12px;
            margin-right: 20px;
            font-size: 1.125rem;
            font-weight: 600;
            color: var(--text-secondary);
            cursor: pointer;
            position: relative;
            transition: var(--transition);
            border-bottom: 2px solid transparent;
        }

        .tab:hover {
            color: var(--primary-hover);
        }

        .login-html .sign-in:checked + .tab,
        .login-html .sign-up:checked + .tab {
            color: var(--primary-color);
            border-bottom: 2px solid var(--primary-color);
        }

        /* ========== ERROR MESSAGE BOX ========== */
        .error-container {
            width: 100%;
            margin-bottom: 20px;
            animation: slideDown 0.4s ease-out forwards;
            position: relative;
        }

        .error-container.hiding {
            animation: fadeOutUp 0.5s ease-in forwards;
            pointer-events: none;
        }

        .error {
            display: flex;
            align-items: center;
            gap: 12px;
            padding: 12px 40px 12px 16px;
            background: var(--error-bg);
            border: 1px solid var(--error-border);
            border-left: 4px solid var(--error-color);
            border-radius: var(--radius);
            color: var(--error-color);
            font-size: 13px;
            font-weight: 500;
            line-height: 1.5;
            box-shadow: var(--shadow-sm);
            position: relative;
            overflow: hidden;
        }

        /* Progress bar for auto-hide timer */
        .error::after {
            content: '';
            position: absolute;
            bottom: 0;
            left: 0;
            height: 3px;
            background: linear-gradient(90deg, var(--error-color), #f87171);
            width: 100%;
            animation: progressBar 5s linear forwards;
            border-radius: 0 0 0 2px;
        }

        .error-icon {
            flex-shrink: 0;
            width: 18px;
            height: 18px;
            fill: var(--error-color);
        }

        /* Close button */
        .error-close {
            position: absolute;
            top: 50%;
            right: 10px;
            transform: translateY(-50%);
            background: none;
            border: none;
            color: var(--error-color);
            font-size: 18px;
            cursor: pointer;
            opacity: 0.5;
            transition: opacity 0.2s;
            line-height: 1;
            padding: 0;
            width: 22px;
            height: 22px;
            display: flex;
            align-items: center;
            justify-content: center;
            border-radius: 4px;
        }

        .error-close:hover {
            opacity: 1;
            background: rgba(220, 38, 38, 0.1);
        }

        @keyframes slideDown {
            from {
                opacity: 0;
                transform: translateY(-15px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        @keyframes fadeOutUp {
            from {
                opacity: 1;
                transform: translateY(0);
                max-height: 100px;
                margin-bottom: 20px;
            }
            to {
                opacity: 0;
                transform: translateY(-15px);
                max-height: 0;
                margin-bottom: 0;
                padding: 0;
            }
        }

        @keyframes progressBar {
            from {
                width: 100%;
            }
            to {
                width: 0%;
            }
        }

        /* ========== Form Container ========== */
        .login-form {
            margin-top: 35px;
            position: relative;
            perspective: 1000px;
            transform-style: preserve-3d;
        }

        .sign-in-htm,
        .sign-up-htm {
            backface-visibility: hidden;
            transition: all 0.4s ease-in-out;
        }

        .sign-in-htm {
            position: relative;
        }

        .sign-up-htm {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            opacity: 0;
            transform: rotateY(180deg);
        }

        .login-html .sign-in:checked ~ .login-form .sign-in-htm {
            transform: rotateY(0deg);
            opacity: 1;
        }

        .login-html .sign-in:checked ~ .login-form .sign-up-htm {
            transform: rotateY(-180deg);
            opacity: 0;
        }

        .login-html .sign-up:checked ~ .login-form .sign-in-htm {
            transform: rotateY(180deg);
            opacity: 0;
        }

        .login-html .sign-up:checked ~ .login-form .sign-up-htm {
            transform: rotateY(0deg);
            opacity: 1;
        }

        /* ========== Form Groups ========== */
        .group {
            margin-bottom: 25px;
        }

        .group:last-child {
            margin-bottom: 0;
        }

        .label {
            display: block;
            color: var(--text-secondary);
            font-size: 0.875rem;
            font-weight: 500;
            margin-bottom: 8px;
            text-transform: uppercase;
            letter-spacing: 0.05em;
        }

        .input {
            width: 100%;
            padding: 12px 15px;
            border: 2px solid var(--border-color);
            border-radius: var(--radius);
            font-size: 1rem;
            color: var(--text-primary);
            background: #fff;
            transition: var(--transition);
            font-family: 'Segoe UI', system-ui, sans-serif;
        }

        .input:focus {
            outline: none;
            border-color: var(--primary-color);
            box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.1);
        }

        .input::placeholder {
            color: #cbd5e1;
        }

        /* ========== Checkbox ========== */
        .check {
            display: none;
        }

        .group label[for="check"] {
            display: flex;
            align-items: center;
            gap: 10px;
            cursor: pointer;
            color: var(--text-secondary);
            font-size: 0.9375rem;
            font-weight: 400;
            text-transform: none;
            letter-spacing: normal;
        }

        .icon {
            width: 18px;
            height: 18px;
            border: 2px solid var(--border-color);
            border-radius: 4px;
            display: inline-block;
            position: relative;
            transition: var(--transition);
        }

        .check:checked + label .icon {
            background: var(--primary-color);
            border-color: var(--primary-color);
        }

        .check:checked + label .icon::after {
            content: '';
            position: absolute;
            top: 2px;
            left: 5px;
            width: 4px;
            height: 8px;
            border: solid white;
            border-width: 0 2px 2px 0;
            transform: rotate(45deg);
        }

        /* ========== Submit Button ========== */
        .button {
            width: 100%;
            padding: 14px 0;
            border: none;
            border-radius: var(--radius);
            background: linear-gradient(135deg, var(--primary-color) 0%, #3b82f6 100%);
            color: #fff;
            font-size: 1rem;
            font-weight: 600;
            text-transform: uppercase;
            letter-spacing: 1px;
            cursor: pointer;
            transition: var(--transition);
            box-shadow: var(--shadow-md);
            font-family: 'Segoe UI', system-ui, sans-serif;
        }

        .button:hover {
            transform: translateY(-2px);
            box-shadow: 0 8px 25px rgba(37, 99, 235, 0.4);
        }

        .button:active {
            transform: translateY(0);
        }

        /* ========== Divider ========== */
        .hr {
            height: 1px;
            background: var(--border-color);
            margin-top: 30px;
            position: relative;
        }

        .hr::after {
            content: 'or';
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: var(--card-bg);
            padding: 0 15px;
            color: var(--text-secondary);
            font-size: 0.875rem;
            font-weight: 500;
        }

        /* ========== Responsive Design ========== */
        @media screen and (max-width: 900px) {
            body {
                justify-content: center;
                padding: 20px;
            }

            .bg-icon,
            .bg-icon-2,
            .bg-icon-3,
            .store-brand {
                display: none;
            }

            .login-wrap {
                max-width: 100%;
                animation: cardEnterMobile 0.8s cubic-bezier(0.34, 1.56, 0.64, 1) 0.2s forwards;
            }
        }

        @media screen and (max-width: 768px) {
            .login-html {
                padding: 35px 25px;
            }

            .tab {
                font-size: 1rem;
                padding: 0 10px 10px;
                margin-right: 15px;
            }
        }

        @media screen and (max-width: 480px) {
            .login-html {
                padding: 30px 20px;
            }

            .input {
                padding: 10px 12px;
            }

            .button {
                font-size: 0.9375rem;
            }
        }

        /* ========== PROFESSIONAL ANIMATIONS ========== */
        
        /* Card entrance - smooth slide with elastic bounce */
        @keyframes cardEnter {
            0% {
                opacity: 0;
                transform: translateX(60px) scale(0.95);
            }
            60% {
                opacity: 1;
                transform: translateX(-5px) scale(1.01);
            }
            80% {
                transform: translateX(2px) scale(0.99);
            }
            100% {
                opacity: 1;
                transform: translateX(0) scale(1);
            }
        }

        /* Mobile card entrance */
        @keyframes cardEnterMobile {
            0% {
                opacity: 0;
                transform: translateY(40px) scale(0.95);
            }
            60% {
                opacity: 1;
                transform: translateY(-5px) scale(1.01);
            }
            80% {
                transform: translateY(2px) scale(0.99);
            }
            100% {
                opacity: 1;
                transform: translateY(0) scale(1);
            }
        }

        /* Content fade in after card appears */
        @keyframes contentFade {
            from {
                opacity: 0;
                transform: translateY(10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        /* Shimmer effect on top gradient line */
        @keyframes shimmer {
            0% {
                background-position: 200% 0;
            }
            100% {
                background-position: -200% 0;
            }
        }

        /* Floating background icons */
        @keyframes floatSlow {
            0%, 100% {
                transform: translate(-50%, -50%) translateY(0);
            }
            50% {
                transform: translate(-50%, -50%) translateY(-20px);
            }
        }
    </style>
</head>
<body>
    <!-- Floating background icons -->
    <i class="fas fa-boxes-stacked bg-icon"></i>
    <i class="fas fa-box bg-icon-2"></i>
    <i class="fas fa-cube bg-icon-3"></i>

    <!-- Store branding -->
    <div class="store-brand">
        <i class="fas fa-boxes-stacked store-icon"></i>
        <div class="store-name">Taha Elgamal STORE</div>
        <div class="store-tagline">Inventory Management System</div>
    </div>

    <!-- Main card on right side with professional animation -->
    <div class="login-wrap">
        <div class="login-html">
            <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
            <label for="tab-1" class="tab">Sign In</label>
            <input id="tab-2" type="radio" name="tab" class="sign-up">
            <label for="tab-2" class="tab">Sign Up</label>
            <div class="login-form">
                <form class="sign-in-htm" action="/item-service-mvc-project/LoginController" method="post">
                
                <%-- Error Message Box (replaces alert) --%>
                    <%
                        String errorMessage = (String) request.getAttribute("errorMessage");
            			String errorMessageValid="Username Not exists";
                        if(errorMessage == errorMessageValid){
                    %>
                        <div class="error-container" id="errorBox">
                            <div class="error">
                                <svg class="error-icon" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-2h2v2zm0-4h-2V7h2v6z"/>
                                </svg>
                                <span><%= errorMessage %></span>
                                <button type="button" class="error-close" onclick="hideError()" title="Dismiss">×</button>
                            </div>
                        </div>
                    <%
                        }
                    %>
                    
                    <div class="group">
                        <label for="user1" class="label">Username</label>
                        <input id="user1" type="text" class="input" name="username" value="${rememberedUser}">
                    </div>
                    <div class="group">
                        <label for="pass2" class="label">Password</label>
                        <input id="pass2" type="password" class="input" data-type="password" name="password">
                    </div>
                    <div class="group">
                        <input id="check" type="checkbox" class="check" checked name="keepLoggedIn">
                        <label for="check"><span class="icon"></span> Remember me</label>
                    </div>
                    
                    <input type="hidden" class="input" name="action" value="returnUser">
                    
                    <div class="group">
                        <input type="submit" class="button" value="Sign In">
                    </div>
                    <div class="hr"></div>
                </form>
                <form class="sign-up-htm" action="/item-service-mvc-project/LoginController" method="post">
                    
                    <%-- Error Message Box (replaces alert) --%>
                    <%
                        String errorMessage1 = (String) request.getAttribute("errorMessage");
                        if(errorMessage1 != null && !(errorMessage1==errorMessageValid)){
                    %>
                        <div class="error-container" id="errorBox">
                            <div class="error">
                                <svg class="error-icon" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-2h2v2zm0-4h-2V7h2v6z"/>
                                </svg>
                                <span><%= errorMessage %></span>
                                <button type="button" class="error-close" onclick="hideError()" title="Dismiss">×</button>
                            </div>
                        </div>
                    <%
                        }
                    %>
                    
                    <div class="group">
                        <label for="user" class="label">Username</label>
                        <input id="user" type="text" class="input" name="username">
                    </div>
                    <div class="group">
                        <label for="email" class="label">Email</label>
                        <input id="email" type="email" class="input" placeholder="example@email.com" name="email">
                    </div>
                    <div class="group">
                        <label for="pass" class="label">Password</label>
                        <input id="pass" type="password" class="input" data-type="password" name="password">
                    </div>
                    <div class="group">
                        <label for="age" class="label">Age</label>
                        <input id="age" type="number" class="input" min="1" max="100" name="age">
                    </div>
                    <div class="group">
                        <label for="phone" class="label">Phone Number</label>
                        <input id="phone" type="tel" class="input" placeholder="+1 (555) 000-0000" name="phoneNumber">
                    </div>
                    
                    <input type="hidden" class="input" name="action" value="createUser">
                    
                    <div class="group">
                        <input type="submit" class="button" value="Sign Up">
                    </div>
                    <div class="hr"></div>
                </form>
            </div>
        </div>
    </div>

    <script>
        // Auto-hide error after 5 seconds
        setTimeout(function() {
            hideError();
        }, 5000);

        function hideError() {
            var errorBox = document.getElementById('errorBox');
            if (errorBox) {
                errorBox.classList.add('hiding');
                // Remove from DOM after animation completes
                setTimeout(function() {
                    if (errorBox.parentNode) {
                        errorBox.parentNode.removeChild(errorBox);
                    }
                }, 500);
            }
        }
    </script>
</body>
</html>