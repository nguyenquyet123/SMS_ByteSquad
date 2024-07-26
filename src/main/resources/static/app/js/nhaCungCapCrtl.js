app.controller("nhaCungCapCtrl", function ($scope, $http) {
    $scope.form = {};
    $scope.suppliers = [];

    const host = 'http://localhost:8080';

    // Reset form
    $scope.reset = () => {
        $scope.form = {};
    }
    
    // Create Supplier
    $scope.create = function () {
        var item = angular.copy($scope.form);
        $http.post(`${host}/api/suppliers`, item).then(response => {
            $scope.suppliers.push(response.data);
            alert("Tạo mới thành công");
            $scope.reset();
        }).catch(error => {
            alert("Tạo mới thất bại");
            console.log("Error:", error);
        });
    }

    // Update Supplier
    $scope.update = function () {
        var item = angular.copy($scope.form);
        $http.put(`${host}/api/suppliers/${item.supplierId}`, item).then(response => {
            var index = $scope.suppliers.findIndex(s => s.supplierId === item.supplierId);
            if (index !== -1) {
                $scope.suppliers[index] = response.data;
            }
            alert("Cập nhật thành công");
            $scope.reset();
        }).catch(error => {
            alert("Cập nhật thất bại");
            console.log("Error:", error);
        });
    }

    // Delete Supplier
    $scope.delete = function (id) {
        if (confirm("Bạn có chắc chắn muốn xóa không?")) {
            $http.delete(`${host}/api/suppliers/${id}`).then(response => {
                $scope.suppliers = $scope.suppliers.filter(s => s.supplierId !== id);
                alert("Xóa thành công");
            }).catch(error => {
                alert("Xóa thất bại");
                console.log("Error:", error);
            });
        }
    }

    // Show Supplier Details
    $scope.selectedRow = null;
    $scope.showDetails = function (row) {
        $scope.form = angular.copy(row);
        $scope.selectedRow = $scope.selectedRow === row ? null : row;
    };

    // Initialize
    $scope.init = () => {
        $http.get(`${host}/api/suppliers`).then(response => {
            $scope.suppliers = response.data;
        }).catch(error => {
            console.log("Error:", error);
        });
    };

    $scope.init();
});
