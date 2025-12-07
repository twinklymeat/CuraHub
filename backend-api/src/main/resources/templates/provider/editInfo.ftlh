<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/editInfo.css">
</head>

<body>
    <div class="success-box">

        <div class="text-div">
            <form id="edit-doctor" method="post" action="/api/users/doctors/update/${user}" enctype="form-data"> 
                <input type="number" id="ID" name="ID" value=${user} style="display: none;">
                <input type="text" id="firstName" name="firstName" value=${doctor.firstName} class="input-box">
                <input type="text" id="lastName" name="lastName" value=${doctor.lastName} class="input-box">
                <input type="email" id="email" name="email" value=${doctor.email} class="input-box">
                <input type="text" id="phone" name="phone" value=${doctor.phone} class="input-box">
                <input type="password" id="password" name="password" placeholder="password" value=${doctor.password} class="input-box">
                <button type="submit" class="continuebutton">
                    Save
                </button>
            </form>
        </div>
    </div>
</body>

</html>