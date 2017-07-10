(function() {
	'use strict';
	angular.module('base.config', []);
	angular.module('base.router', ['ui.router', 'base.config']);
	angular.module('main.index', ['base.router', 'base.config', 'ui.router']);
	
})();


(function() {
	'use strict';
	var config = angular.module('base.config');
	var BaseUrl = '/shop/';
	var projectUrl = 'http://localhost:8080';
	var configStates = {
		states : []
	};
	config.constant('BaseUrl', BaseUrl);
	config.constant('ProjectUrl', projectUrl);
	config.constant('States', configStates);
})();