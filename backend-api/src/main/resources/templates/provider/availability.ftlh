<!DOCTYPE html>

<head>
    <link rel="stylesheet" type="text/css" href="/css/signup.css">
</head>

<body>
    <div class="signup-box">
        <form id="new-user" action="/api/availability/add" method="post">
            <input type="number" id="doctor" name="doctor" value=${doctorID} style="display: none;">
            <div class="infodiv">
                Day:
                <br>
                <input type="text" id="day" name="day" class="input-field" placeholder="e.g. Sunday">
            </div>
            <div class="infodiv">
                Start Time:
                <br>
                <input type="text" id="startTime" name="startTime" class="input-field" placeholder="e.g. 6:00 AM">
            </div>
            <div class="infodiv">
                End Time:
                <br>
                <input type="text" id="endTime" name="endTime" class="input-field" placeholder="e.g. 6:00 PM">
            </div>
            <div class="infodiv">
                Appt. Length:
                <br>
                <input type="text" id="length" name="length" class="input-field" placeholder="e.g. 1 hr">
            </div>
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