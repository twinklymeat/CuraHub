<!DOCTYPE html>

<head>
    <link rel="stylesheet" type="text/css" href="/css/signup.css">
</head>

<body>
    <div class="signup-box">
        <form id="new-user" action="/api/doctors" method="post">
            <input type="number" id="user" name="user" style="display: none;" value="${userID?c}">
            <div class="infodiv">
                Description:
                <br>
                <input type="text" id="description" name="description" class="input-field" placeholder="">
            </div>
            <div class="infodiv">
                <button type="submit" class="enter-button">ENTER</button>
            </div>
        </form>
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