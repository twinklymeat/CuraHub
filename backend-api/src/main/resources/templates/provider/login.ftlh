<!DOCTYPE html>

<head>
    <link rel="stylesheet" type="text/css" href="/css/login.css">
</head>

<body>
    <div class="login-box">
        <form id="login" action="/doctor/login/validate" method="get">
            <div class="infodiv">
                Email:
                <br>
                <input type="email" id="email" name="email" class="input-field" placeholder="Enter email...">
            </div>
            <div class="infodiv">
                Password:
                <br>
                <input type="password" id="password" name="password" class="input-field"
                    placeholder="Enter password...">
            </div>
            <button class="enter-button" type="submit">
                ENTER
            </button>

        </form>
        <!-- <div class="infodiv">
            <a href="success.html">
                <button class="enter-button">
                    Enter
                </button>
            </a>
        </div> -->
    </div>
</body>