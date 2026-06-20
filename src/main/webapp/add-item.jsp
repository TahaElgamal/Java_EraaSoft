<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Add Item</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
  <style>
    /* ========== CSS Variables ========== */
    :root {
      --primary-color: #2563eb;
      --primary-hover: #1d4ed8;
      --success-color: #16a34a;
      --success-hover: #15803d;
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
      background: linear-gradient(135deg, #f1f5f9 0%, #e2e8f0 100%);
      color: var(--text-primary);
      min-height: 100vh;
      display: flex;
      align-items: center;
      justify-content: center;
      padding: 20px;
      line-height: 1.6;
    }

    /* ========== Main Container ========== */
    .container {
      max-width: 600px;
      width: 100%;
      background: var(--card-bg);
      padding: 50px 45px;
      border-radius: var(--radius);
      box-shadow: var(--shadow-lg);
      position: relative;
      overflow: hidden;
    }

    .container::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 4px;
      background: linear-gradient(90deg, var(--primary-color), #60a5fa);
    }

    /* ========== Title ========== */
    .text {
      text-align: center;
      color: var(--text-primary);
      font-size: 2.25rem;
      font-weight: 700;
      margin-bottom: 40px;
      position: relative;
      padding-bottom: 15px;
    }

    .text::after {
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

    /* ========== Error Message ========== */
    .error-container {
      width: 100%;
      margin-bottom: 25px;
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
      padding: 14px 40px 14px 18px;
      background: var(--error-bg);
      border: 1px solid var(--error-border);
      border-left: 4px solid var(--error-color);
      border-radius: var(--radius);
      color: var(--error-color);
      font-size: 14px;
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
      width: 20px;
      height: 20px;
      fill: var(--error-color);
    }

    /* Close button */
    .error-close {
      position: absolute;
      top: 50%;
      right: 12px;
      transform: translateY(-50%);
      background: none;
      border: none;
      color: var(--error-color);
      font-size: 20px;
      cursor: pointer;
      opacity: 0.5;
      transition: opacity 0.2s;
      line-height: 1;
      padding: 0;
      width: 24px;
      height: 24px;
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
        margin-bottom: 25px;
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

    /* ========== Form Layout ========== */
    form {
      padding: 0 10px;
    }

    .form-row {
      display: flex;
      flex-wrap: wrap;
      gap: 30px;
      margin-bottom: 30px;
    }

    .form-row:last-of-type {
      margin-bottom: 40px;
    }

    .input-data {
      width: calc(50% - 15px);
      position: relative;
      height: 50px;
    }

    .form-row .input-data:only-child {
      width: calc(50% - 15px);
    }

    /* ========== Input Fields ========== */
    .input-data input {
      display: block;
      width: 100%;
      height: 100%;
      border: none;
      border-bottom: 2px solid var(--border-color);
      font-size: 16px;
      font-weight: 500;
      color: var(--text-primary);
      padding: 0 5px;
      background: transparent;
      transition: var(--transition);
    }

    .input-data input:focus {
      outline: none;
      border-bottom-color: var(--primary-color);
    }

    /* Floating Labels */
    .input-data input:focus ~ label,
    .input-data input:valid ~ label {
      transform: translateY(-28px);
      font-size: 13px;
      color: var(--primary-color);
      font-weight: 600;
    }

    .input-data label {
      position: absolute;
      bottom: 12px;
      left: 5px;
      color: var(--text-secondary);
      font-size: 16px;
      font-weight: 400;
      pointer-events: none;
      transition: var(--transition);
    }

    /* Animated Underline */
    .underline {
      position: absolute;
      bottom: 0;
      height: 2px;
      width: 100%;
    }

    .underline::before {
      position: absolute;
      content: "";
      height: 100%;
      width: 100%;
      background: linear-gradient(90deg, var(--primary-color), #60a5fa);
      transform: scaleX(0);
      transform-origin: center;
      transition: transform 0.4s ease;
    }

    .input-data input:focus ~ .underline::before,
    .input-data input:valid ~ .underline::before {
      transform: scaleX(1);
    }

    /* ========== Submit Button ========== */
    .button {
      display: block;
      width: 100%;
      padding: 14px 0;
      border: none;
      border-radius: var(--radius);
      background: linear-gradient(135deg, var(--success-color) 0%, #22c55e 100%);
      color: #fff;
      font-size: 16px;
      font-weight: 600;
      text-transform: uppercase;
      letter-spacing: 1px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
      transition: var(--transition);
      box-shadow: var(--shadow-md);
    }

    .button:hover {
      transform: translateY(-2px);
      box-shadow: 0 8px 25px rgba(22, 163, 74, 0.4);
    }

    .button:active {
      transform: translateY(0);
    }

    /* ========== Back Link ========== */
    .back {
      text-align: center;
      margin-top: 30px;
    }

    .back a {
      display: inline-block;
      color: var(--primary-color);
      text-decoration: none;
      font-size: 15px;
      font-weight: 500;
      position: relative;
      padding-bottom: 3px;
      transition: var(--transition);
    }

    .back a::after {
      content: '';
      position: absolute;
      bottom: 0;
      left: 0;
      width: 0;
      height: 2px;
      background: linear-gradient(90deg, var(--primary-color), #60a5fa);
      transition: width 0.3s ease;
    }

    .back a:hover {
      color: var(--primary-hover);
    }

    .back a:hover::after {
      width: 100%;
    }

    /* ========== Responsive Design ========== */
    @media screen and (max-width: 768px) {
      .container {
        padding: 35px 25px;
      }

      .text {
        font-size: 1.75rem;
      }

      .input-data {
        width: 100% !important;
      }

      .form-row {
        gap: 20px;
        margin-bottom: 20px;
      }

      .button {
        font-size: 15px;
      }
    }

    @media screen and (max-width: 480px) {
      .text {
        font-size: 1.5rem;
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

    .container {
      animation: fadeIn 0.4s ease-out forwards;
    }
  </style>
</head>
<body>

<div class="container">
  <div class="text">
    Add Item
  </div>

  <%-- Error Message Section --%>
  <%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if(errorMessage != null){
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

  <form action="/item-service-mvc-project/itemController"  >
    <div class="form-row">
      <div class="input-data">
        <input type="text" name="nameItem" required>
        <div class="underline"></div>
        <label >Name</label> 
      </div>
      <div class="input-data">
        <input type="text" name="priceItem" required>
        <div class="underline"></div>
        <label>PRICE</label>
      </div>
    </div>
    <div class="form-row">
      <div class="input-data">
        <input type="text" name="totalNumberItem" required>
        <div class="underline"></div>
        <label>TOTAL_NUMBER</label>
      </div>
     <input type="hidden" name="action" value="add-item" required>
    </div>
    
     <input type="submit" value="Add" class="button">
   
  </form>

  <p class="back">
    <a href="/item-service-mvc-project/itemController" >Back To Items</a>
  </p>
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