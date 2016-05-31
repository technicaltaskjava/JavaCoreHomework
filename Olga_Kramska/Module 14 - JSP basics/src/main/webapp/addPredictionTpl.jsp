<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<tr id="${prediction.id}">
  <td>${prediction.cookie}</td>
  <td>${prediction.prediction}</td>
  <td><fmt:formatDate value="${prediction.timeAdded}" type="both"/></td>
  <td>
    <button class="edit-cookie icon-btn"></button>
    <button class="delete-cookie icon-btn"></button>
  </td>
</tr>
