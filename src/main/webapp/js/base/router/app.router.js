(function() {
	'use strict';
	var router = angular.module('base.config');
	router.config([ 'States', 'BaseUrl', function(States, BaseUrl) {
		var states = States.states;
		var baseUrl = BaseUrl;
		states.push({
			name : 'empty',
			obj : {
				url : '/',
				views : {
					'' : {
						templateUrl : baseUrl + 'html/index/content.html'
					},
					'header@empty' : {
						templateUrl : baseUrl + 'html/index/header.html'
					}
				}
			}
		});
		states.push({
			name : 'empty.home',
			obj : {
				url : 'home',
				views : {
					"body@empty" : {
						templateUrl : baseUrl +'html/index/home.html'
					}
				}
			}
		});
		
		states.push({
			name : 'empty.title',
			obj : {
				url : 'home',
				views : {
					"body@empty" : {
						templateUrl : baseUrl +'html/index/home.html'
					}
				}
			}
		});
	} ]);

})();