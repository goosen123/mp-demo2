/**
 * 用户详情对话框（可用于添加和修改对话框）
 */
var OrdersInfoDlg = {
    userInfoData: {},
    validateFields: {
        account: {
            validators: {
                notEmpty: {
                    message: '账户不能为空'
                }
            }
        },
        userName: {
            validators: {
                notEmpty: {
                    message: '姓名不能为空'
                }
            }
        },
        //citySel: {
            //validators: {
                //notEmpty: {
                    //message: '部门不能为空'
                //}
            //}
        //},
        password: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                },
                identical: {
                    field: 'rePassword',
                    message: '两次密码不一致'
                },
            }
        },
        rePassword: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                },
                identical: {
                    field: 'password',
                    message: '两次密码不一致'
                },
            }
        }
    }
};

/**
 * 清除数据
 */
OrdersInfoDlg.clearData = function () {
    this.userInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrdersInfoDlg.set = function (key, val) {
    this.userInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrdersInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
OrdersInfoDlg.close = function () {
    parent.layer.close(window.parent.Orders.layerIndex);
};

/**
 * 点击部门input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
OrdersInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#citySel").attr("value", instance.getSelectedVal());
    $("#deptid").attr("value", treeNode.id);
};

/**
 * 显示部门选择的树
 *
 * @returns
 */
OrdersInfoDlg.showDeptSelectTree = function () {
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
OrdersInfoDlg.showInfoDeptSelectTree = function () {
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
OrdersInfoDlg.hideDeptSelectTree = function () {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
};

/**
 * 收集数据
 */
OrdersInfoDlg.collectData = function () {
    this.set('id').set('account').set('userSex').set('password').set('avatar')
        .set('userEmail').set('userName').set('rePassword').set('userPhone');//.set('birthday').set('deptid')
};

/**
 * 验证两个密码是否一致
 */
OrdersInfoDlg.validatePwd = function () {
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
OrdersInfoDlg.validate = function () {
    $('#userInfoForm').data("bootstrapValidator").resetForm();
    $('#userInfoForm').bootstrapValidator('validate');
    return $("#userInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加用户
 */
OrdersInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    if (!this.validatePwd()) {
        Feng.error("两次密码输入不一致");
        return;
    }
    
    //初始一些必填值
    this.userInfoData['userType'] = 1;
    this.userInfoData['status'] = 1;

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mgr/add", function (data) {
    	if(data.code == 1){
    		Feng.success("添加成功!");
            window.parent.MgrUser.table.refresh();
            OrdersInfoDlg.close();
    	}else{
    		Feng.error("添加失败!"+data.message);
    	}
    }, function (data) {
        Feng.error("添加失败!"+data.responseJSON.message);
    });
    ajax.set(this.userInfoData);
    ajax.setBeanData();
    ajax.start();
};

/**
 * 提交修改
 */
OrdersInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    
    //初始一些必填值
    this.userInfoData['userType'] = 1;
    this.userInfoData['status'] = 1;

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/mgr/edit", function (data) {
    	if(data.code == 1){
    		Feng.success("修改成功!");
            window.parent.MgrUser.table.refresh();
            OrdersInfoDlg.close();
    	}else{
    		Feng.error("修改失败!"+data.message);
    	}
    }, function (data) {
    	Feng.error("修改失败!"+data.responseJSON.message);
    });
    ajax.set(this.userInfoData);
    ajax.setBeanData();
    ajax.start();
};

/**
 * 获取订单信息详情
 */
OrdersInfoDlg.getOrdersDetail = function () {
	var id = $("#id").val();
    if(id == null || id == 'undefined' || id == ''){
    	return;
    }
    var data = {};
	data['id'] = id;
	//提交请求
    var ajax = new $ax(Feng.ctxPath + "/orders/getDetail", function (data) {
    	if(data.code == 1){
     	   $("#userName").val(data.data.userName);
     	   $("#code").val(data.data.code);
     	   $("#orderTitle").val(data.data.orderTitle);
     	   $("#totalCost").val(data.data.totalCost);
		   $("#totalVolume").val(data.data.totalVolume);
		   $("#orderStatus").val(data.data.orderStatus);
		   $("#orderRemark").val(data.data.orderRemark);
		   $("#createTime").val(data.data.createTime);
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
OrdersInfoDlg.chPwd = function () {
	
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
        OrdersInfoDlg.hideDeptSelectTree();
    }
}

$(function () {
    //Feng.initValidator("ordersInfoForm", OrdersInfoDlg.validateFields);
    
    //如果是修改的话，获取详情
    OrdersInfoDlg.getOrdersDetail();

});
