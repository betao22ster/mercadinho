angular.module('homer').controller(
		'produtoscad',
		function($scope, $http, sweetAlert, $location, $stateParams) {

			$scope.produto = {};

			$scope.novo = function() {
				$scope.produto = {};
				$scope.inclusao = true;
				$scope.signup_form.submitted = false;
			}

			$scope.gravar = function() {

				$scope.signup_form.submitted = true;

				if (!$scope.signup_form.$valid) {

					return null;
				}

				sweetAlert.swal({
					title : "Confirmação",
					text : "Deseja confirmar?",
					showCancelButton : true,
					confirmButtonColor : "#DD6B55",
					confirmButtonText : "Sim",
					cancelButtonText : "Não",
					closeOnConfirm : true,
					closeOnCancel : true
				}, function(isConfirm) {

					if (isConfirm) {
						$scope.gravarOK();
					}

				});
			}

			$scope.gravarOK = function() {

				$scope.processoGravacao = {};
				$scope.processoGravacao.link = '/servico/produto/incluir';

				if ($scope.produto.id != null) {
					console.log("Modo alteração");
					$scope.processoGravacao.link = '/servico/produto/alterar';
				}

				$http.post($scope.processoGravacao.link, $scope.produto)
						.success(function(data) {
							console.log("gravacao ok");

							$scope.produto = data;

							sweetAlert.swal({
								title : "Informação",
								text : "Registro gravado com sucesso."
							});

							$scope.inclusao = false;

						}).error(function(data) {
							console.log("failed");

							sweetAlert.swal({
								title : "Alerta",
								text : data.message
							});

						});

			}

			$scope.findFotos = function() {

				if ($scope.produto.id == null) {
					console.log("Não vai buscar fotos.");
					return;
				}

				console.log("consultando fotos...");

				$http.get('/servico/fotos/findImagens/' + $scope.produto.id)
						.success(function(data) {

							console.log("Fotos ok.");

							$scope.listaFotos = data;

						});

			}

			if ($stateParams.produto != null) {
				$scope.produto = $stateParams.produto;
				$scope.findFotos();
			}

			$scope.produtofile = {};

			$scope.anexarFoto2 = function() {

				var form = new FormData();
				form.append("file", $("#upload-file-input")[0]);

				$.ajax({
					url : "/servico/fotos/upload",
					type : "POST",
					data : new FormData($("#upload-file-form")[0]),
					enctype : 'multipart/form-data',
					processData : false,
					contentType : false,
					cache : false,
					success : function() {

						$scope.findFotos();

						sweetAlert.swal({
							title : "Informação",
							text : "Imagem gravado com sucesso."
						});

					},
					error : function(data) {

						sweetAlert.swal({
							title : "Alerta",
							text : data.responseText
						});

					}
				});
			}

		});
