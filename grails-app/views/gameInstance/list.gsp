
<%@ page import="edu.nps.fauxgame.GameInstance" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gameInstance.label', default: 'GameInstance')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-gameInstance" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-gameInstance" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'gameInstance.dateCreated.label', default: 'Date Created')}" />
					
						<th><g:message code="gameInstance.gameTitle.label" default="Game Title" /></th>
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'gameInstance.lastUpdated.label', default: 'Last Updated')}" />
					
						<th><g:message code="gameInstance.lobbyServer.label" default="Lobby Server" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${gameInstanceInstanceList}" status="i" var="gameInstanceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${gameInstanceInstance.id}">${fieldValue(bean: gameInstanceInstance, field: "dateCreated")}</g:link></td>
					
						<td>${fieldValue(bean: gameInstanceInstance, field: "gameTitle")}</td>
					
						<td><g:formatDate date="${gameInstanceInstance.lastUpdated}" /></td>
					
						<td>${fieldValue(bean: gameInstanceInstance, field: "lobbyServer")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${gameInstanceInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
