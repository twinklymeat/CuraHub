<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>CuraHub — Patient Dashboard</title>
<link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<div class="wrap">
  <a class="btn secondary" href="../landing.html">Log Out</a>

  <div class="card" style="margin-top:12px">
    <h1 class="title">Your Dashboard</h1>
    <p class="subtitle">Here are your upcoming appointments and quick actions.</p>

    <div class="card" style="margin:12px 0">
      <h2 style="margin-bottom:8px;font-size:1.25rem">Upcoming Appointments</h2>
      <div class="doctor-grid">
        <div class="card doctor-card">
          <img class="avatar" src="https://i.pravatar.cc/400?img=44" alt="Doctor Emily C">
          <div class="meta">
            <h3>Dr. Emily Carter — Family Medicine</h3>
            <div class="tags">
              <span class="tag">Wed, Oct 8 · 10:30 AM</span>
              <span class="tag">In-person</span>
              <span class="tag">Room 204</span>
            </div>
          </div>
          <div class="actions-right">
            <button class="btn secondary" onclick="alert('Demo: reschedule not implemented')">Reschedule</button>
            <button class="btn" onclick="alert('Demo: cancel not implemented')">Cancel</button>
          </div>
        </div>

        <div class="card doctor-card">
          <img class="avatar" src="https://i.pravatar.cc/400?img=59" alt="Doctor Noah P">
          <div class="meta">
            <h3>Dr. Noah Patel — Cardiology</h3>
            <div class="tags">
              <span class="tag">Mon, Oct 20 · 2:00 PM</span>
              <span class="tag">Video visit</span>
            </div>
          </div>
          <div class="actions-right">
            <button class="btn secondary" onclick="alert('Demo: reschedule not implemented')">Reschedule</button>
            <button class="btn" onclick="alert('Demo: cancel not implemented')">Cancel</button>
          </div>
        </div>
      </div>
      <small class="muted" style="display:block;margin-top:8px">Static demo data</small>
    </div>

    <div class="actions" style="display:flex;gap:10px;flex-wrap:wrap;margin-top:8px">
      <a class="btn secondary" href="./patient_profile.html">Modify Profile</a>
      <a class="btn" href="./dashboard_available_drs.html">See Available Doctors</a>
    </div>
  </div>
</div>
</body>
</html>


