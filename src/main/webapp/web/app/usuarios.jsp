<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Panel de control</title>
	<jsp:include page="componentesApp/libreriasPanel.jsp"/>
</head>
<body>
<main class="d-flex flex-nowrap">
	<jsp:include page="componentesApp/sidebar.jsp"/>
	
    <section id="favourites">
      
  
  <ul id="myTab" class="nav nav-tabs" role="tablist">
  <li class="nav-item"><a class="nav-link active show" href="#tab-1" data-bs-target=".etab-p1, .etabi-img1" data-bs-toggle="tab">C1</a></li>
  <li class="nav-item"><a class="nav-link" href="#tab-2" data-bs-target=".etab-p2, .etabi-img2" data-bs-toggle="tab">C2</a></li>
  <li class="nav-item"><a class="nav-link" href="#tab-3" data-bs-target=".etab-p3, .etabi-img3" data-bs-toggle="tab">C3</a></li>
  <li class="nav-item"><a class="nav-link" href="#tab-4" data-bs-target=".etab-p4, .etabi-img4" data-bs-toggle="tab">C4</a></li>
  <li class="nav-item"><a class="nav-link" href="#tab-5" data-bs-target=".etab-p5, .etabi-img5" data-bs-toggle="tab">C5</a></li>
  <li class="nav-item"><a class="nav-link" href="#tab-6" data-bs-target=".etab-p6, .etabi-img6" data-bs-toggle="tab">C6</a></li>
</ul>
<div class="tab-content">
  <div class="tab-pane fade show active etab-p1">
    <p>Content 1.</p>
  </div>
  <div class="tab-pane fade etab-p2">
    <p>Content 2.</p>
  </div>
  <div class="tab-pane fade etab-p3">
    <p>Content 3.</p>
  </div>
  <div class="tab-pane fade etab-p4">
    <p>Content 4.</p>
  </div>
  <div class="tab-pane fade etab-p5">
    <p>Content 5.</p>
  </div>
  <div class="tab-pane fade etab-p6">
    <p>Content 6.</p>
  </div>
</div>
<div class="tab-content">
  <div class="tab-pane fade show active etabi-img1">
    <p>Content 111.</p>
  </div>
  <div class="tab-pane fade etabi-img2">
    <p>Content 2222.</p>
  </div>
  <div class="tab-pane fade etabi-img3">
    <p>Content 3333.</p>
  </div>
  <div class="tab-pane fade etabi-img4">
    <p>Content 4444.</p>
  </div>
  <div class="tab-pane fade etabi-img5">
    <p>Content 5555.</p>
  </div>
  <div class="tab-pane fade etabi-img6">
    <p>Content 6666.</p>
  </div>
</div>
  
  
  

        <script>
        $('#myTab a[data-bs-toggle="tab"]').on('show.bs.tab', function(e) {
        	  let target = $(e.target).data('bs-target')
        	  $(target)
        	    .addClass('active show')
        	    .siblings('.tab-pane.active')
        	    .removeClass('active show')
        	})
        </script>
      </section>
</main>					
</body>
</html>