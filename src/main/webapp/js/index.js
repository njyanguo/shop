
(function(){
	"use strict";
	var mainApp = angular.module("main.index");
	mainApp.controller("indexCtrl", ['$scope','$http', 'BaseUrl', 'ProjectUrl', function($scope, $http, BaseUrl, ProjectUrl){
		$scope.baseUrl = BaseUrl;
		$scope.categorySubList = [];
		$scope.initParameters = function(){
			$http({
				method: 'post',
				url: ProjectUrl + BaseUrl+ 'index/index.do',
				params: null,
				headers: {
	                'Content-Type': 'application/x-www-form-urlencoded'
	            }
			}).success(function(data,header,config,status){
				$scope.advertList = data[0].AdvertList;
				$scope.noticeList = data[0].NoticeList;
				$scope.categoryList = data[0].CategoryList;
			}).error(function(data,header,config,status){
				console.log("error");
			});
			
		}
		$scope.initParameters();
		
	}]);
	
})();

