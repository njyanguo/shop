(function(){
	'use strict';
	var app = angular.module('main.index');
	app.directive('carousel', ['BaseUrl', function(BaseUrl){
		var baseUrl = BaseUrl;
		var linker = function($scope, $elem, $attrs){
			console.log($scope);
			var carouselEle = angular.element('.carousel');
			carouselEle.carousel({
				  interval: 5000,
				  pause: 'hover',
				  wrap: true
			});
			/*angular.element('#carousel_prev').on('click', function(){
				carouselEle.carousel('prev');
			});
			angular.element('#carousel_next').on('click', function(){
				carouselEle.carousel('next');
			});*/
		}
		
		return {
			restrict: 'A',
			templateUrl : baseUrl + 'html/index/carousel.html',
			link: linker
		}
	}]);
	app.directive('mouseOverLeave', ['$http', 'BaseUrl', 'ProjectUrl', function($http, BaseUrl, ProjectUrl){
		var httpService = function($scope){
			$http({
				method: 'post',
				url: ProjectUrl+ BaseUrl+ 'index/queryCategorySubList.do',
				params: {
					categoryId: $scope.category.categoryId
				},
				headers: {
	                'Content-Type': 'application/x-www-form-urlencoded'
	            }
			}).success(function(data,header,config,status){
				$scope.CategorySubList = data[0].CategorySubList;
			}).error(function(data,header,config,status){
				console.log("error");
			});
		}
        return {
            restrict: 'A',
            scope: false,
            link: function(scope, elem, attr){
                elem.bind('mouseover', function(){
                	httpService(scope);
                	angular.element(".category-content .category-list li.first .menu-in").css("display","none");
                	angular.element(".category-content .category-list li.first").removeClass("hover");
                	elem.addClass("hover");
                	elem.children("div.menu-in").css("display","block");
                });
                elem.bind('mouseleave', function(){
                	elem.removeClass("hover");
                	elem.children("div.menu-in").css("display","none");
                });
            }
        }
    }]);
})();