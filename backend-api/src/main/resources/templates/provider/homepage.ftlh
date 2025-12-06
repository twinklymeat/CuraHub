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
                    Availability:
                    <#list availability as av>
                            <br>
                            ${av.day} ${av.startTime} - ${av.endTime}; ${av.length}
                            <!-- <form action="/api/availability/delete/${av.ID}" method="post"> -->
                            <a href="/api/availability/delete/${av.ID}">
                                <button type="submit" style="inline-size: auto; height: auto; font-size: inherit; border: none; background-color: royalblue; border-radius: 5px; color:#f1f1f1; cursor: pointer;">delete</button>
                            </a>
                            <!-- </form> -->
                    </#list>
                    <br>
                    <a href="/doctor/add-availability/${id}">
                        <button style="inline-size: auto; height: auto; font-size: inherit; border: none; background-color: royalblue; border-radius: 5px; color:#f1f1f1; cursor: pointer;;">Add Availability</button>
                    </a>
                    <!-- Office: The building that looks like Ferb's head -->
                </div>
            </div>
            <div>
                <a href="/doctor/edit/${id}"><button class="editButton">Edit</button></a>
            </div>
            <div>
                <a href="/doctor/edit/description/${id}"><button class="editButton" style="float:left; height: 50px;">Edit Desc.</button></a>
            </div>
        </div>
        <p style="font-size: 20px;">
            Scheduled Appointments:
        </p>
        <div class="schedule">

            <#list appointments as ap>
                <div class="appointment">
                    <div class="appointment-info">
                        <name>
                            ${ap.user.name}
                        </name>
                        <br>${ap.time}<br>
                        <div class="reason">
                            ${ap.notes}
                        </div>
                    </div>
                </div>
            </#list>
            <!-- <div class="appointment">
                <img src="perry-the-platypus.png" class="appointment-img">
                <div class="appointment-info">
                    <name>
                        Perry The Platypus?!?!
                    </name>
                    <br>09/18/25 10:00 AM<br>
                    <div class="reason">
                        Reason: <br>
                        To defeat evil
                    </div>
                </div>
            </div> -->
        </div>

    </div>

</body>