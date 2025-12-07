<!DOCTYPE html>

<head>
    <link rel="stylesheet" type="text/css" href="/css/signup.css">
</head>

<body>
    <div class="signup-box">
        <form id="new-user" action="/api/users/doctors" method="post">
            <div class="infodiv">
                First Name:
                <br>
                <input type="text" id="firstName" name="firstName" class="input-field" placeholder="">
            </div>
            <div class="infodiv">
                Last Name:
                <br>
                <input type="text" id="lastName" name="lastName" class="input-field" placeholder="">
            </div>
            <div class="infodiv">
                Email:
                <br>
                <input type="email" id="email" name="email" class="input-field">
            </div>
            <div class="infodiv">
                Phone:
                <br>
                <input type="text" id="phone" name="phone" class="input-field">
            </div>
            <div class="infodiv">
                Password:
                <br>
                <input type="password" id="password" name="password" class="input-field">
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