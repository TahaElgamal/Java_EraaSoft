<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Update Item</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
  <style>
    /* ========== CSS Variables (Matching show-items.css & add-item.css) ========== */
    :root {
      --primary-color: #2563eb;
      --primary-hover: #1d4ed8;
      --success-color: #16a34a;
      --success-hover: #15803d;
      --warning-color: #d97706;
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
      max-width: 800px;
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
      width: calc(33.333% - 20px);
      position: relative;
      height: 50px;
    }

    /* For rows with 2 items */
    .form-row:has(.input-data:nth-child(2):last-child) .input-data {
      width: calc(50% - 15px);
    }

    /* For single item rows */
    .form-row:has(.input-data:only-child) .input-data {
      width: calc(33.333% - 20px);
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

    /* Disabled Input Styling */
    .input-data input:disabled {
      border-bottom-color: #cbd5e1;
      color: var(--text-secondary);
      cursor: not-allowed;
      background: #f8fafc;
    }

    .input-data input:disabled ~ label {
      color: var(--text-secondary);
    }

    /* Floating Labels */
    .input-data input:focus ~ label,
    .input-data input:valid ~ label,
    .input-data input:disabled ~ label {
      transform: translateY(-28px);
      font-size: 13px;
      color: var(--primary-color);
      font-weight: 600;
    }

    .input-data input:disabled ~ label {
      color: var(--text-secondary);
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

    .input-data input:disabled ~ .underline::before {
      background: #cbd5e1;
      transform: scaleX(1);
    }

    /* ========== Submit Button ========== */
    .button {
      display: block;
      width: 100%;
      padding: 14px 0;
      border: none;
      border-radius: var(--radius);
      background: linear-gradient(135deg, var(--warning-color) 0%, #f59e0b 100%);
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
      box-shadow: 0 8px 25px rgba(217, 119, 6, 0.4);
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
        max-width: 100%;
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
      Update Item
    </div>
    <form action="/item-service-mvc-project/itemController">
      <div class="form-row">
        <div class="input-data">
          <input type="text" disabled name="idItem" value="${itemSelected.id}">
          <div class="underline"></div>
          <label>ID</label>
        </div>
        <div class="input-data">
          <input type="text" required name="nameItem" value="${itemSelected.name}">
          <div class="underline"></div>
          <label>Name</label>
        </div>
        <div class="input-data">
          <input type="text" required name="priceItem" value="${itemSelected.price}">
          <div class="underline"></div>
          <label>PRICE</label>
        </div>
      </div>
      <div class="form-row">
        <div class="input-data">
          <input type="text" required name="totalNumberItem" value="${itemSelected.totalNumber}">
          <div class="underline"></div>
          <label>TOTAL_NUMBER</label>
        </div>
        <input type="hidden" name="action" value="update-item" required>
        <input type="hidden" name="id" value="${itemSelected.id}" required>
      </div>
      
      <input type="submit" value="Update" class="button">
    </form>

    <p class="back">
      <a href="/item-service-mvc-project/itemController" >Back To Items</a>
    </p>
  </div>
</body>
</html>