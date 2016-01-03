<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tr>
	<td>\${ index }</td>
	<td><a href="<c:url value="/event" />?id=\${ id }">\${ title }</a></td>
	<td><a href="<c:url value="/user" />?id=\${ memID }">\${ mem }</a></td>
	<td>\${ date }</td>
</tr>