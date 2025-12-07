<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>CuraHub — Patient Login</title>
<link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="wrap">
  <a class="btn secondary" href="../landing.html">← Back</a>
  <div class="card">
    <h1 class="title">Welcome back</h1>
    <p class="subtitle">Log in to manage your appointments and health records.</p>
    <form onsubmit="window.location.href='./dashboard_patient.html'; return false;">
      <label>Email</label>
      <input type="email" placeholder="jane@example.com" required>
      <label>Password</label>
      <input type="password" placeholder="••••••••" required>
      <div class="actions">
        <button class="btn primary" type="submit">Log In</button>
        <a class="btn secondary" href="./patient_signup.html">Create an account</a>
      </div>
      <small>This is a prototype screen.</small>
    </form>
  </div>
</div>
</body>
</html>