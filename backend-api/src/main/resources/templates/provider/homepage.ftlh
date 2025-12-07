<!DOCTYPE html>

<head>
    <link rel="stylesheet" type="text/css" href="/css/homepage.css">
</head>

<body>
    <div class="mainbox">
        <div class="profilebox">
            <!-- <img src="doctorpfp.png" style="float: left;"> -->
            <div class="profile-info">
                Dr. ${doctor.user.lastName}, ${doctor.user.firstName}
                <div class="profile-expertise">
                    Description: ${doctor.description};
                    <br>
                    Contact: ${doctor.user.email}, ${doctor.user.phone}
                    <br>
                    <div class="availability">
                        <a href="/doctor/add-availability/${id}">
                            <button class="shadow" style="height: auto; font-size: inherit; border: none; background-color: green; border-radius: 5px; color:#f1f1f1; cursor: pointer; float: right;">+</button>
                        </a>
                        Availability:
                        <#list availability as av>
                                <div style="margin: 5px; padding: 0px; height: min-content;">
                                    ${av.day} ${av.startTime} - ${av.endTime}; ${av.length}
                                    <!-- <form action="/api/availability/delete/${av.ID}" method="post"> -->
                                    <a href="/api/availability/delete/${av.ID}">
                                        <button type="submit" class="shadow" style="inline-size: auto; height: auto; font-size: inherit; border: none; background-color:firebrick; border-radius: 5px; color:#f1f1f1; cursor: pointer; float: right;margin-left: 5px;">X</button>
                                    </a>
                                    <a href="/doctor/availability?avID=${av.ID}">
                                        <button type="submit" class="shadow" style="inline-size: auto; height: auto; font-size: inherit; border: none; background-color:darkblue; border-radius: 5px; color:#f1f1f1; cursor: pointer; float: right;margin-left: 20px;">🔧</button>
                                    </a>
                                    <!-- </form> -->
                                </div>
                        </#list>
                    </div>
                    <br>
                    <!-- Office: The building that looks like Ferb's head -->
                </div>
            </div>
        </div>
        <div>
            <div>
                <a href="/doctor/edit/${id}"><button class="editButton shadow" style="float: left;">Edit Info</button></a>
            </div>
            <div>
                <a href="/doctor/edit/description/${id}"><button class="shadow editButton">Edit Desc.</button></a>
            </div>
        </div>
        <div>
            <p style="font-size: 20px;">
                Scheduled Appointments:
            </p>
            <div class="schedule">

                <#list appointments as ap>
                    <div class="appointment">
                        <div class="appointment-info">
                            <name>
                                ${ap.user.name} 
                                <a href="/doctor/patient/${ap.user.ID}?drID=${doctor.ID}">
                                    <button class="editButton" style="height: 36px; float: right;">View</button>
                                </a>
                            </name>
                            <br>${ap.date}<br>
                            <div class="reason">
                                ${ap.notes}
                            </div>
                        </div>
                    </div>
                </#list>
                
            </div>

            <p style="font-size: 20px;">
                Past Appointments:
            </p>
            <div class="schedule">
                <#list pastAppt as ap>
                    <div class="appointment">
                        <div class="appointment-info">
                            <name>
                                ${ap.user.name}
                                <a href="/doctor/patient/${ap.user.ID}?drID=${doctor.ID}">
                                    <button class="editButton" style="height: 36px; float: right;">View</button>
                                </a>
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