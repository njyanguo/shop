(function(){
	'use strict';
	var router = angular.module('main.index');
	router.config(['$stateProvider', '$urlRouterProvider', 'BaseUrl', 'States', 'ProjectUrl',
	    function($stateProvider, $urlRouterProvider,BaseUrl, States, ProjectUrl){
			$urlRouterProvider.otherwise('home');
			var states = States.states;
			angular.forEach(states, function(state){
				$stateProvider.state(state.name, state.obj);
			});
	}]);
})();