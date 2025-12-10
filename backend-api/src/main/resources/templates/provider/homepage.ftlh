<!DOCTYPE html>

<head>
    <link rel="stylesheet" type="text/css" href="/css/homepage.css">
</head>

<body style="padding: 10px;">
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
                        <div style="float: right; width: min-content;">
                            <a href="/doctor/patient/${ap.user.ID}?drID=${doctor.ID}">
                                <button class="editButton" style="">View User</button>
                            </a>
                            <form action="/api/doctors/email/cancel"  method="post">
                                <input type="text" name="to" id="to" style="display: none;" value=${ap.user.email}>
                                <input type="text" name="subject" id="subject" style="display: none;" value="Appointment on ${ap.date} Cancelled">
                                <input type="text" name="text" id="text" style="display: none;" value="Dr. ${ap.doctor.user.name} has decided to cancel your appointment, if you have any questions you can email them here: ${ap.doctor.user.email} or call their office at ${ap.doctor.user.phone}. Thank You, CuraHub">
                                <input type="number" name="apID" id="apID" style="display: none;" value=${ap.ID}>
                                <button type="submit" class="editButton" style="background-color: firebrick; margin-top: 10px;">Cancel Appt.</button>
                            </form>
                        </div>
                        <div class="appointment-info">
                            <name>
                                ${ap.user.name} 
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
                        <div style="float: right; width: min-content;">
                            <a href="/doctor/patient/${ap.user.ID}?drID=${doctor.ID}">
                                <button class="editButton" style="">View User</button>
                            </a>
                            <!-- <form action="/api/doctors/email/cancel"  method="post">
                                <input type="text" name="to" id="to" style="display: none;" value=${ap.user.email}>
                                <input type="text" name="subject" id="subject" style="display: none;" value="Appointment on ${ap.date} Cancelled">
                                <input type="text" name="text" id="text" style="display: none;" value="Dr. ${ap.doctor.user.name} has decided to cancel your appointment, if you have any questions you can email them here: ${ap.doctor.user.email}. Thank You, CuraHub">
                                <input type="number" name="apID" id="apID" style="display: none;" value=${ap.ID}>
                                <button type="submit" class="editButton" style="background-color: firebrick; margin-top: 10px;">Cancel Appt.</button>
                            </form> -->
                        </div>
                        <div class="appointment-info">
                            <name>
                                ${ap.user.name}
                            </name>
                            <br>${ap.date}<br>
                            <div class="reason">
                                ${ap.notes}
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
            <div style="font-size: 20px; width: fit-content;">
                Reviews
            </div>
            <div style="font-size: 20px; float: right; width: fit-content;">
                Average Reviews: ${avRevs}
            </div>
            <div class="schedule">
                <#list reviews as rv>
                    <div class="appointment">
                        <div class="appointment-info">
                            <name>
                                ${rv.user.name}
                            </name>
                            <div style="float: right; font-size: 20px;">
                                rating: ${rv.rating} / 5 ⭐
                            </div><br>
                            <div class="reason" style="font-size: 15px;">
                                ${rv.content} <br><br>
                                <#if rv.response??>
                                    Response:
                                    <div class="reason" style="font-size: 15px;">
                                        ${rv.response}
                                    </div>
                                    <form action="/api/reviews/doctor/delete" method="get">
                                        <input type="number" name="ID" id="ID" placeholder="id" style="display: none;" value=${rv.ID}>
                                        <button type="submit" class="editButton" style="float: right;">delete /edit</button>
                                    </form>
                                <#else>
                                    Response:
                                    <form action="/api/reviews/doctor/add" enctype="form-data" method="post">
                                        <input type="number" name="ID" id="ID" placeholder="id" style="display: none;" value=${rv.ID}> 
                                        <input type="text" name="response" id="response" placeholder="Response" class="input-box">
                                        <button type="submit" class="editButton" style="width: 100px; float: right;">Respond</button>
                                    </form>
                                </#if>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </div>

</body>