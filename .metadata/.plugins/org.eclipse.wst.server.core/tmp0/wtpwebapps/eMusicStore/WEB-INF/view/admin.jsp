<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/view/template/header.jsp"%>


    <div class = "container-wrapper">
        <div class = "container">
           <div class="page-header">
            <h1>Administrator Page</h1>
            
            <h3>
              <a href="<c:url value="/admin/productInventory" /> ">Product Inventory</a>
            </h3>
            </div>
           <p>This part is for add , edit and delete product </p>    
           
            <h1>Administrator Page</h1>
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <h2>
                Welcome: ${pageContext.request.userPrincipal.name} | <a href="<c:url value="/login?logout" />">Logout</a>
            </h2>
        </c:if>
            
            <h3>
              <a href="<c:url value="/admin/productInventory/productAdd" /> ">Add Product</a>
            </h3>
            </div>
           <p>This part is for add  product </p>
                  
           
  <%@ include file="/WEB-INF/view/template/footer.jsp" %>
      