<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/css/homepage.css">
    </head>
    <body>
        <div class="mainbox">
            <a href="/doctor/dashboard/${doctorID}"><button class="editButton returnButton shadow" style="height: 36px;"> < Back</button></a>
            <div class="profilebox">
                <div class="profile-info">
                    User: ${user.name}
                    <p style="font-size: 25px;">
                        Email: ${user.email} <br>
                        Phone: ${user.phone}

                    </p>
                </div>
            </div>
            <p style="font-size: 20px;">
                Scheduled Appointments:
            </p>
            <div>
                <div class="schedule" style="font-size: large;">
                    <#list appointments as ap>
                        <div class="appointment">
                            <div class="appointment-info">
                                <name>
                                    ${ap.user.name} <br>
                                    Dr. ${ap.doctor.user.name}
                                </name>
                                <br>${ap.date}<br>
                                <div class="reason">
                                    ${ap.notes}
                                </div>
                            </div>
                        </div>
                    </#list>
                    
                </div>
            </div>
            <p style="font-size: 20px;">
                Past Appointments:
            </p>
            <div>
                <div class="schedule" style="font-size: large;">
                    <#list pastAppts as ap>
                        <div class="appointment">
                            <div class="appointment-info">
                                <name>
                                    ${ap.user.name} <br>
                                    Dr. ${ap.doctor.user.name}
                                </name>
                                <br>${ap.date}<br>
                                <div class="reason">
                                    ${ap.notes}
                                </div>
                            </div>
                        </div>
                    </#list>
                    
                </div>
            </div>
        </div>
    </body>
</html>