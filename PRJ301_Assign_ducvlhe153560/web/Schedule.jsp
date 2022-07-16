<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Time Table</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <a class="navbar-brand" href="#">Check Attedance </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
                    aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="TeachingSchedule">Schedule </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="GroupStudent">Display Group</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <form method="get" action="TeachingSchedule" class="viewTimeTable">
                <div> Campus: 
                    <select name="campus" required>
                        <c:forEach var="c" items="${clist}" >
                            <option value="${c}">${c}</option>
                        </c:forEach>
                    </select><br>
                </div>
                <div>
                    Lecture: <input type="text" name="lecture" required>     
                    <input type="submit" value="View">
                </div>
            </form>
            <div class="row">
                <table class="table table-bordered ">
                    <thead class="bg-primary">
                        <tr>
                            <th>Slot</th>
                            <th>Date </th>
                            <th>Group</th>
                            <th>Subject </th>
                            <th>Room</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="s" items="${slist}">
                            <tr>
                                <td style="width: 10%;">Slot${s.getSlot()}</td>
                                <td style="width: 16%;">${s.getDate()}</td>
                                <td style="width: 16%; ">${s.getGroup().getCode()}</td>
                                <td style="width: 16%;">${s.getGroup().getSubject().getCode()}</td>
                                <td style="width: 16%;">${s.getRoom().getCode()}</td>
                                <td style="width: 16%;">${s.isStatus()?"<p style='color:green;'>Attend</p>":"<p style='color:red;'>Not yet</p>"}</td>
                                <c:if test="${s.isStatus()}">
                                    <th><a href="CheckAttend?sid=${s.getId()}&status=${s.isStatus()}">View</a></th>
                                </c:if>
                                <c:if test="${!s.isStatus()}">
                                    <th><a href="CheckAttend?sid=${s.getId()}&status=${s.isStatus()}">Check</a></th>

                                </c:if>
                            </tr>
                        </c:forEach>



                    </tbody>
                </table>
            </div>
        </div>


        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
    </body>

</html>