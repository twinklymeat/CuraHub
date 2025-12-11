<!DOCTYPE html>

<head>
    <link rel="stylesheet" type="text/css" href="/css/signup.css">
</head>

<body>
    <div class="signup-box">
        <form id="new-user" action="/api/appointments/doctor" method="post">
            <div class="infodiv">
                User id:
                <br>
                <input type="number" id="user" name="user" class="input-field">
            </div>
            <div class="infodiv">
                Time:
                <br>
                <input type="datetime-local" id="time" name="time" class="input-field">
            </div>
            <div class="infodiv">
                Reason:
                <br>
                <input type="text" id="notes" name="notes" class="input-field">
            </div>
            
            <input type="number" id="doctor" name="doctor" class="input-field" style="display: none; " value=${drID}>
            <!-- <div class="infodiv">
                DOB:
                <br>
                <input type="date" class="input-field">
            </div> -->
            <!-- <div class="infodiv">
                About Me (optional):
                <br>
                <textarea rows="3" class="input-field" style="font-size: 20px; margin-bottom: -10px;"></textarea>
            </div> -->
            <!-- <div class="infodiv" style="margin-bottom: 15px;">
                Gender:
                <br>
                <select class="input-field">
                    <option value="male">male</option>
                    <option value="female">female</option>
                    <option value="other">other</option>
                </select>
            </div> -->
            <div class="infodiv">
                <button class="enter-button" type="submit">Submit</button>
            </div>
        </form>
    </div>
</body>