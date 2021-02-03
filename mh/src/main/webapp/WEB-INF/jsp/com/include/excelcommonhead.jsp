<%@ page language="java" contentType="application/vnd.ms-excel;charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/jsp/flash21/include/compage.jsp" />


<!-- jQuery -->
	<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
	<%--    <script type="text/javascript" src="<c:url value='/js/jquery/jquery-1.12.4.min.js'/>"></script>
   <script type="text/javascript" src="<c:url value='/js/jquery/jquery-migrate-1.4.1.min.js'/>"></script>
   <script type="text/javascript" src="<c:url value='/js/jquery/jquery.form.js'/>"></script>
   <script type='text/javascript' src='<c:url value='/js/jquery/jQuery.slide_v2.js'/>'></script>
   <script type='text/javascript' src='<c:url value='/js/jquery/jquery.cycle.js'/>'></script>
   <script type='text/javascript' src='<c:url value='/js/jquery/jquery.touchslider.min.js'/>'></script>
   <script type='text/javascript' src='<c:url value='/js/jquery/jquery-ui-1.8.21.custom.min.js'/>'></script>
   <script type='text/javascript' src='<c:url value='/js/jquery/jquery.mmenu.min.al.js'/>'></script>
   <script type='text/javascript' src='<c:url value='/js/jquery/jquery.ui.mouse.js'/>'></script>
   <script type='text/javascript' src='<c:url value='/js/jquery/jquery.ui.sortable.js'/>'></script>
   <script type='text/javascript' src='<c:url value='/js/jquery/jquery.ui.widget.js'/>'></script>
   <script type='text/javascript' src='<c:url value='/js/jquery/jquery.blockUI.js'/>'></script>
   <script type='text/javascript' src='<c:url value='/js/jquery/tree/jquery.treeview.js'/>'></script>
   <script type='text/javascript' src='<c:url value='/js/jquery/dropdownbanner.js'/>'></script> --%>
	
	<script type="text/javascript">
		var contextPath = "${pageContext.request.contextPath}";
	</script>
	
	
	
	<!-- Global site tag (gtag.js) - Google Analytics-->
    <script async="" src="https://www.googletagmanager.com/gtag/js?id=UA-118965717-3"></script>
    
    <script>
      window.dataLayer = window.dataLayer || [];

      function gtag() {
        dataLayer.push(arguments);
      }
      gtag('js', new Date());
      // Shared ID
      gtag('config', 'UA-118965717-3');
      // Bootstrap ID
      gtag('config', 'UA-118965717-5');
    </script>
    
    <!-- Plugins and scripts required by this view-->
    <script src="${contextPath}/js/main.js"></script>
    
	<script>
	    /* $(function(){
	        $(".table-responsive-sm").dataTable();
	    }); */
 	  $(function(){
	        $(".table-responsive-sm").dataTable({
	            order : [[0, 'desc']] 
	           });
	    });
	</script>
	
	<!-- CoreUI and necessary plugins-->
    <script src="${contextPath}/vendors/jquery/jquery.min.js"></script>
    <script src="${contextPath}/vendors/popper.js/popper.min.js"></script>
    <script src="${contextPath}/vendors/bootstrap/js/bootstrap.min.js"></script>
    <script src="${contextPath}/vendors/pace-progress/js/pace.min.js"></script>
    <script src="${contextPath}/vendors/perfect-scrollbar/js/perfect-scrollbar.min.js"></script>
	<script src="${contextPath}/vendors/coreui/js/coreui.min.js"></script>
    <!-- Plugins and scripts required by this view-->
    <script src="${contextPath}/vendors/chartjs/js/Chart.min.js"></script>
    <%-- <script src="${contextPath}/vendors/coreui/js/custom-tooltips.min.js"></script> --%>
	<script src="${contextPath}/vendors/datatables-plugins/jquery.dataTables.js"></script>
   <script  src="${contextPath}/vendors/datatables-plugins/dataTables.bootstrap4.js"></script>
   <script  src="${contextPath}/vendors/datatables-plugins/dataTables.dataTables.js"></script>
