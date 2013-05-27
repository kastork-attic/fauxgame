<%@ page import="edu.nps.fauxgame.GameInstance" %>



<div class="fieldcontain ${hasErrors(bean: gameInstanceInstance, field: 'gameTitle', 'error')} required">
	<label for="gameTitle">
		<g:message code="gameInstance.gameTitle.label" default="Game Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="gameTitle" name="gameTitle.id" from="${edu.nps.fauxgame.GameTitle.list()}" optionKey="id" required="" value="${gameInstanceInstance?.gameTitle?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstanceInstance, field: 'lobbyServer', 'error')} required">
	<label for="lobbyServer">
		<g:message code="gameInstance.lobbyServer.label" default="Lobby Server" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="lobbyServer" name="lobbyServer.id" from="${edu.nps.fauxgame.LobbyServer.list()}" optionKey="id" required="" value="${gameInstanceInstance?.lobbyServer?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gameInstanceInstance, field: 'positions', 'error')} ">
	<label for="positions">
		<g:message code="gameInstance.positions.label" default="Positions" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${gameInstanceInstance?.positions?}" var="p">
    <li><g:link controller="position" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="position" action="create" params="['gameInstance.id': gameInstanceInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'position.label', default: 'Position')])}</g:link>
</li>
</ul>

</div>

