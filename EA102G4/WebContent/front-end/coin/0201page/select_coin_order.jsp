<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>有誰課金: bitch</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>課金: 稽查</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Coin Order: Home</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllCoinOrder.jsp'>List</a> all CoinOrders.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="coin.do" >
        <b>輸入訂單編號:</b>
        <input type="text" name="coin_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="coinSvc" scope="page" class="com.coin.model.CoinService" />
   
  <li>
     <FORM METHOD="post" ACTION="coin.do" >
       <b>選擇訂單編號:</b>
       <select size="1" name="coin_id">
         <c:forEach var="coinVO" items="${coinSvc.all}" > 
          <option value="${coinVO.coin_id}">${coinVO.coin_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="coin.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="mem_id">
         <c:forEach var="coinVO" items="${coinSvc.all}" > 
          <option value="${coinVO.mem_id}">${coinVO.mem_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getMem_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>新增訂單</h3>

<ul>
  <li><a href='addCoinOrder.jsp'>Add</a> a new Emp.</li>
</ul>

</body>
</html>