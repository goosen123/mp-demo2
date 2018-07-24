/**
 * 商品详情对话框（可用于添加和修改对话框）
 */
var ProductInfoDlg = {
    productInfoData: {},
    validateFields: {
    	productTitle: {
            validators: {
                notEmpty: {
                    message: '标题不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
ProductInfoDlg.clearData = function () {
    this.productInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProductInfoDlg.set = function (key, val) {
    this.productInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ProductInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
ProductInfoDlg.close = function () {
    parent.layer.close(window.parent.Product.layerIndex);
};

/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
ProductInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#citySel").attr("value", instance.getSelectedVal());
    $("#deptid").attr("value", treeNode.id);
};

/**
 * 显示部门选择的树
 *
 * @returns
 */
ProductInfoDlg.showDeptSelectTree = function () {
    var cityObj = $("#citySel");
    var cityOffset = $("#citySel").offset();
    $("#menuContent").css({
        left: cityOffset.left + "px",
        top: cityOffset.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 显示用户详情部门选择的树
 *
 * @returns
 */
ProductInfoDlg.showInfoDeptSelectTree = function () {
    var cityObj = $("#citySel");
    var cityPosition = $("#citySel").position();
    $("#menuContent").css({
        left: cityPosition.left + "px",
        top: cityPosition.top + cityObj.outerHeight() + "px"
    }).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
};

/**
 * 隐藏部门选择的树
 */
ProductInfoDlg.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

/**
 * 收集数据
 */
ProductInfoDlg.collectData = function () {
    this.set('id').set('code').set('coverPic').set('productTitle').set('productBrief')
        .set('productDesc').set('originalPrice').set('salePrice');
};

/**
 * 验证两个密码是否一致
 */
ProductInfoDlg.validatePwd = function () {
    var password = this.get("password");
    var rePassword = this.get("rePassword");
    if (password == rePassword) {
        return true;
    } else {
        return false;
    }
};

/**
 * 验证数据是否为空
 */
ProductInfoDlg.validate = function () {
    $('#productInfoForm').data("bootstrapValidator").resetForm();
    $('#productInfoForm').bootstrapValidator('validate');
    return $("#productInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加商品
 */
ProductInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/product/add", function (data) {
    	if(data.code == 1){
    		Feng.success("添加成功!");
            window.parent.Product.table.refresh();
            ProductInfoDlg.close();
    	}else{
    		Feng.error("添加失败!"+data.message);
    	}
    }, function (data) {
        Feng.error("添加失败!"+data.responseJSON.message);
    });
    ajax.set(this.productInfoData);
    ajax.setBeanData();
    ajax.start();
};

/**
 * 提交修改
 */
ProductInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/product/edit", function (data) {
    	if(data.code == 1){
    		Feng.success("修改成功!");
            window.parent.Product.table.refresh();
            ProductInfoDlg.close();
    	}else{
    		Feng.error("修改失败!"+data.message);
    	}
    }, function (data) {
    	Feng.error("修改失败!"+data.responseJSON.message);
    });
    ajax.set(this.productInfoData);
    ajax.setBeanData();
    ajax.start();
};

/**
 * 获取商品信息详情
 */
ProductInfoDlg.getProductDetail = function () {
	var id = $("#id").val();
    if(id == null || id == 'undefined' || id == ''){
    	return;
    }
    var data = {};
	data['id'] = id;
	//提交请求
    var ajax = new $ax(Feng.ctxPath + "/product/getDetail", function (data) {
    	if(data.code == 1){
     	   $("#code").val(data.data.code);
     	   $("#coverPic").val(data.data.coverPic);
     	   $("#productTitle").val(data.data.productTitle);
     	   $("#productBrief").val(data.data.productBrief);
		   $("#productDesc").val(data.data.productDesc);
		   $("#originalPrice").val(data.data.originalPrice);
		   $("#salePrice").val(data.data.salePrice);
		}
    }, function (data) {
    	;
    });
    ajax.setType('get');
    ajax.set(data);
    ajax.start();
};

/**
 * 修改密码
 */
ProductInfoDlg.chPwd = function () {
	
	var paramsData = {};
	var oldPwd = $("#oldPwd").val();
	if(oldPwd == null || oldPwd == ''){
		Feng.error("原密码不能空!");
		return;
	}
	var newPwd = $("#newPwd").val();
	if(newPwd == null || newPwd == ''){
		Feng.error("新密码不能空!");
		return;
	}
	var rePwd = $("#rePwd").val();
	if(rePwd == null || rePwd == ''){
		Feng.error("验证密码不能空!");
		return;
	}
	paramsData['oldPwd'] = hex_md5(oldPwd);
	paramsData['newPwd'] = hex_md5(newPwd);
	paramsData['rePwd'] = hex_md5(rePwd);
    var ajax = new $ax(Feng.ctxPath + "/mgr/changePwd", function (data) {
    	if(data.code == 1){
    		Feng.success("修改成功!");
    	}else{
    		Feng.error("修改失败!"+data.message);
    	}
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(paramsData);
    ajax.setBeanData();
    ajax.start();

};

function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(
            event.target).parents("#menuContent").length > 0)) {
        ProductInfoDlg.hideDeptSelectTree();
    }
}

$(function () {
    Feng.initValidator("productInfoForm", ProductInfoDlg.validateFields);
    
    //如果是修改的话，获取详情
    ProductInfoDlg.getProductDetail();

});
