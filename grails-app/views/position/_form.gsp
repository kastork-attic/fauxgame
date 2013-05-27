<%@ page import="edu.nps.fauxgame.Position" %>



<div class="fieldcontain ${hasErrors(bean: positionInstance, field: 'state', 'error')} ">
	<label for="state">
		<g:message code="position.state.label" default="State" />
		
	</label>
	<g:select name="state" from="${positionInstance.constraints.state.inList}" value="${positionInstance?.state}" valueMessagePrefix="position.state" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: positionInstance, field: 'gameInstance', 'error')} required">
	<label for="gameInstance">
		<g:message code="position.gameInstance.label" default="Game Instance" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="gameInstance" name="gameInstance.id" from="${edu.nps.fauxgame.GameInstance.list()}" optionKey="id" required="" value="${positionInstance?.gameInstance?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: positionInstance, field: 'paramName', 'error')} ">
	<label for="paramName">
		<g:message code="position.paramName.label" default="Param Name" />
		
	</label>
	<g:textField name="paramName" value="${positionInstance?.paramName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: positionInstance, field: 'playerIdent', 'error')} ">
	<label for="playerIdent">
		<g:message code="position.playerIdent.label" default="Player Ident" />
		
	</label>
	<g:textField name="playerIdent" value="${positionInstance?.playerIdent}"/>
</div>

