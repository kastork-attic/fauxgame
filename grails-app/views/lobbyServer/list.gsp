
<%@ page import="edu.nps.fauxgame.LobbyServer" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'lobbyServer.label', default: 'LobbyServer')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-lobbyServer" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-lobbyServer" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="baseURL" title="${message(code: 'lobbyServer.baseURL.label', default: 'Base URL')}" />
					
						<g:sortableColumn property="gameBot" title="${message(code: 'lobbyServer.gameBot.label', default: 'Game Bot')}" />
					
						<g:sortableColumn property="lobbyPassword" title="${message(code: 'lobbyServer.lobbyPassword.label', default: 'Lobby Password')}" />
					
						<g:sortableColumn property="lobbyUsername" title="${message(code: 'lobbyServer.lobbyUsername.label', default: 'Lobby Username')}" />
					
						<g:sortableColumn property="profile" title="${message(code: 'lobbyServer.profile.label', default: 'Profile')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${lobbyServerInstanceList}" status="i" var="lobbyServerInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${lobbyServerInstance.id}">${fieldValue(bean: lobbyServerInstance, field: "baseURL")}</g:link></td>
					
						<td>${fieldValue(bean: lobbyServerInstance, field: "gameBot")}</td>
					
						<td>${fieldValue(bean: lobbyServerInstance, field: "lobbyPassword")}</td>
					
						<td>${fieldValue(bean: lobbyServerInstance, field: "lobbyUsername")}</td>
					
						<td>${fieldValue(bean: lobbyServerInstance, field: "profile")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${lobbyServerInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
