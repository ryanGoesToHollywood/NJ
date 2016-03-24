var njapp = angular.module('njApp', []);
njapp.controller('mainCtrl', function($scope, $http) {
	
	//toggle homepage to page2
	$scope.switchme = true;
    $scope.search = function() {
    	$scope.switchme = false;} //when button pressed switch to page2
    
    //GET from keywords
	$scope.get = function(query){
		$http.get("rest/problem/get?id="+query).success( function(response) {
		$scope.problemsOb = response;
		sessionStorage.setItem("pid", query);
		sessionStorage.setItem("cat1", response.problemCategory1);
		sessionStorage.setItem("cat2", response.problemCategory2);
		sessionStorage.setItem("cat3", response.problemCategory3);
		sessionStorage.setItem("cat4", response.problemCategory4);
		if ($scope.problemsOb.problemCategory1 == null) {
		$scope.noProblemExists = "No solutions yet on NJ.  Add your problem?"};
		})
	}
	
    //GET problem from category1 select
	$scope.cat1 = function(){
		$http.get("rest/problem/getCat1?id="+sessionStorage.getItem("cat1")).success( function(response) {
		$scope.catProbs = response;
		})}	
	
    //GET problem from category2 select
	$scope.cat2 = function(){
		$http.get("rest/problem/getCat2?id="+sessionStorage.getItem("cat2")).success( function(response) {
		$scope.catProbs = response;
		})}	
	
    //GET problem from category3 select
	$scope.cat3 = function(){
		$http.get("rest/problem/getCat3?id="+sessionStorage.getItem("cat3")).success( function(response) {
		$scope.catProbs = response;
		})}	
	
    //GET problem from category4 select
	$scope.cat4 = function(){
		$http.get("rest/problem/getCat4?id="+sessionStorage.getItem("cat4")).success( function(response) {
		$scope.catProbs = response;
		})}	
	
	//Get solutions from category1/lowest level list of choices
	$scope.goToProblemResults = function(goP) {
			$http.get("rest/problem/get?id="+goP).success( function(response) {
			$scope.problemsOb = response;
			$scope.query = goP;
			sessionStorage.setItem("pid", goP);
			sessionStorage.setItem("cat1", response.problemCategory1);
			sessionStorage.setItem("cat2", response.problemCategory2);
			sessionStorage.setItem("cat3", response.problemCategory3);
			sessionStorage.setItem("cat4", response.problemCategory4);
			})}
	
    //POST problem into db after query
	$scope.post = function(query){
		 $http({
		        method : "POST",
		        url : "rest/problem/post",
		        data: JSON.stringify({"problem": query}),
		    }).then(function mySucces(response) {
		        $scope.myWelcome = response.data;
		    }, function myError(response) {
		        $scope.myWelcome = response.statusText;
		})}
	
    //POST solution
	$scope.postS = function(solutionSuggestion, solutionSuggestionURL){
		var pid = sessionStorage.getItem("pid");
		 $http({
		        method : "POST",
		        url : "rest/solution/post",
		        data: JSON.stringify({"solution": solutionSuggestion, "exploreURL": solutionSuggestionURL, "problem": {"problem": pid}}),
		    }).then(function mySucces(response) {
		        $scope.myWelcome = response.data;
		    }, function myError(response) {
		        $scope.myWelcome = response.statusText;
		})}
	
    //INCREMENT sweetJuice for the solution
	$scope.postSweetSol = function(solId){
		$http.get("rest/solution/sweetSol?id="+solId).success( function(response) {
		//$scope. = response;
		})}	

    //INCREMENT sourJuice for the solution
	$scope.postSourSol = function(solId){
		$http.get("rest/solution/sourSol?id="+solId).success( function(response) {
		//$scope. = response;
		})}	
	
    //INCREMENT redAlert for the solution
	$scope.postFlagSol = function(solId){
		$http.get("rest/solution/flagSol?id="+solId).success( function(response) {
		//$scope. = response;
		})}		
	
});

//Custom directive - enter data in textbox
njapp.directive('myEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keypress", function (event) {
            if(event.which === 13) {
                scope.$apply(function (){
                	scope.switchme = false;  //when text entered switch to page2
                    scope.$eval(attrs.myEnter);
                });
                event.preventDefault();
            }
        });
    };
});