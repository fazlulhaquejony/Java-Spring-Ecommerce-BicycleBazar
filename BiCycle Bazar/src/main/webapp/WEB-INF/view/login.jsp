<%@ include file="/WEB-INF/view/template/header.jsp"%>

<div class ="container-wrapper">
  <div class="login-container">
  
    <div id="login-box">
      
      <h2>Login with UserName and Password</h2>
      <c:if test="${not empty msg}">
         <div clas="msg">${msg}</div>
      </c:if>
      <form name="loginForm" action="<c:url value="/j_spring_security_check" />" method="post">
      
      <c:if test ="${not empty error}">
         <div class="error" style="color:#ff000;">${error}</div> 
       </c:if>
       
       <div class="form-group">
       <label for="username">User:</label>
       <input type="text" name="username" id="username" class="form-control"/>
       </div>
       
       <div class="form-group">
       <label for="password">password</label>
       <input type="password" name="password" id="password" class="form-control">
       </div>

      <input type="submit" value="submit" class="btn btn-default">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
      </form> 
    </div>
  
  </div>
<%@ include file="/WEB-INF/view/template/footer.jsp"%>