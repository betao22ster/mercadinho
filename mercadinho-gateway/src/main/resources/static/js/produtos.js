angular
    .module('homer')
	.controller('produtos', 
	function($scope, $http, sweetAlert, $location, $stateParams) {
		
		$scope.listaProduto = [];
		
		$scope.carregar = function(){
			$http.get("/servico/produto/findAll").success(function(data) {
				
				console.log("consulta ok");
				$scope.listaProduto = data;
				
			}).error(function(data) {
				console.log("failed");
				
				sweetAlert.swal({
		            title: "Alerta",
		            text: data.message
				});
				
			});
		}
		
		$scope.carregar();
		
		$scope.excluir = function(item){
			
	        sweetAlert.swal({
	            title: "Confirmação",
	            text: "Deseja confirmar exclusão?",
	            showCancelButton: true,
	            confirmButtonColor: "#DD6B55",
	            confirmButtonText: "Sim",
	            cancelButtonText: "Não",
	            closeOnConfirm: true,
	            closeOnCancel: true },
	        function (isConfirm) {
	            
	            if (isConfirm) {
	            	$scope.excluirOK(item);
	            } 
	            
	        });
		}
		
		$scope.excluirOK = function(item){

			$http.post('/servico/produto/excluir', item).success(function(data) {
				
				console.log("exclusao ok");
				$scope.carregar();
				
			}).error(function(data) {
				console.log("failed");
				
				sweetAlert.swal({
		            title: "Alerta",
		            text: data.message
				});
				
			});
			
		}
		
	}
);
