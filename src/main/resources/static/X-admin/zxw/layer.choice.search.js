﻿// =====================
//   Copyright BOOJOB
//======================

!function($) {

	var backdropLayerTpl = '<div class="choice_backdrop fade"></div>';
	var htmlLayerTpl = [
			'<div class="choice">',
	            '<div class="choice_dialog">',
	                '<div class="choice_content pie_about">',
	                    '<div class="choice_header">',
							'<span class="title B_choice_title"></span>',
	                        '<span class="max_remind B_choice_max"></span>',
	                        '<a href="javascript:;" class="close B_dismiss_choice"></a>',
						'</div>',
	                    '<div class="choice_body">',
	                    	'<div class="listed_group" id="B_listed_group">',
	                    		'<div class="left_text">已选择：</div>',
	                    		'<div class="center_text" id="B_listed_content"></div>',
	                    		'<a href="javascript:;" class="right_text" id="B_listed_clear">清空</a>',
	                    		'<div class="clear"></div>',
	                    	'</div>',
	                    	'<div class="B_choice_content"></div>',
	                    '</div>',
	                    '<div class="choice_footer">',
	                        '<div class="res_add_but">',
	                        	'<div class="butlist">',
	                            	'<div class="btn_blue B_hoverbut btn_100_38 B_btnyes">确 定</div>',
	                            '</div>',
	                            '<div class="butlist">',
	                            	'<div class="btn_lightgray B_hoverbut btn_100_38 B_dismiss_choice B_btncancel">取 消</div>',
	                            '</div>',
	                            '<div class="clear"></div>',
	                        '</div>',
	                    '</div>',
	                    '<input type="hidden" class="B_btnload" />',
	                '</div>',
	            '</div>',
	        '</div>'
		].join('');



    //显示行业分类
	$('#show_Trade').on('click', function() {
		var titleValue = $(this).data('title');
		var multipleValue = eval($(this).data('multiple'));
		var maxNumValue = eval($(this).data('maxnum'));
		var widthValue = eval($(this).data('width'));
		var htmlTrade = '';

		if (JT_trade) {
			htmlTrade += '<div class="choice_body_box choice_body_box1">';
			htmlTrade += '<ul class="list_nav1">';
			if (multipleValue) {
				for (var i = 0; i < JT_trade.length; i++) {
					if (JT_trade[i].split(',')) {
						var iArray = JT_trade[i].split(',');
						htmlTrade += [
								'<li>',
									'<label>',
										'<input class="B_list_trade" type="checkbox" data-code="' + iArray[0] + '" data-title="' + iArray[1] + '"> ',
									'' + iArray[1] + '</label>',
								'</li>',
						].join('');
					};
				};
			} else {
				for (var i = 0; i < JT_trade.length; i++) {
					if (JT_trade[i].split(',')) {
						var iArray = JT_trade[i].split(',');
						htmlTrade += [
								'<li>',
									'<label class="B_list_trade" type="checkbox" data-code="' + iArray[0] + '" data-title="' + iArray[1] + '">',
									'' + iArray[1] + '</label>',
								'</li>',
						].join('');
					};
				};
			}
			htmlTrade += '<div class="clear"></div>';
			htmlTrade += '</ul>';
			htmlTrade += '</div>';
		};

		prepareModal(titleValue, multipleValue, maxNumValue);

		$('.B_choice_content').html(htmlTrade);
	    $('.B_btnyes').attr('id', 'B_btnyes_trade');

		$('.choice_dialog').css({
			width: widthValue + 'px',
	    	left: ($(window).width() - widthValue)/2,
	    	top: ($(window).height() - $('.choice_dialog').outerHeight())/2 + $(document).scrollTop()
	    });

	    $('.choice_backdrop').addClass('in');

		// 恢复选中
	    var recoverValue = $('#show_Trade .code_Trade').val();
		if (recoverValue.length) {
			var recoverValueArray = recoverValue.split(',');
			for (var i = 0; i < recoverValueArray.length; i++) {
				$('.B_list_trade').each(function(index, el) {
					if ($(this).data('code') == recoverValueArray[i]) {
						$(this).prop('checked', !0);
						$(this).closest('li').addClass('current');
					};
				});
			};
			if (multipleValue) {
				copyTradeSelected();
			};
		};

		// 行业列表点击
		$('.B_list_trade').on('click', function() {
			if (multipleValue) {
				if ($(this).is(':checked')) {
					$(this).closest('li').addClass('current');
					var checkedArray = $('.B_list_trade:checked');
					if (checkedArray.length > maxNumValue) {
						disapperTooltip("remind", '最多选择'+ maxNumValue +'个');
						$(this).prop('checked', 0);
						$(this).closest('li').removeClass('current');
						return false;
					} else {
						copyTradeSelected();
					}
				} else {
					$(this).closest('li').removeClass('current');
					copyTradeSelected();
				}
			} else {
				$(this).closest('li').addClass('current');
				var code = $(this).data('code');
				var title = $(this).data('title');
				var tmptitle = title;
				if(tmptitle.length > 7){
                    tmptitle = tmptitle.substring(0,7)+"...";
			    }
				
				$('#show_Trade .code_Trade').val(code);
				$('#show_Trade .title_Trade').text(tmptitle);
				$('#show_Trade .title_Trade').attr('title', title);
				$('.choice_backdrop').remove();
 				$('.choice').remove();
			}
		});

		function copyTradeSelected() {
	    	var htmlListed = '';
	    	$('.B_list_trade:checked').each(function(index, el) {
	    		var listedCode = $(this).data('code');
	    		var listedTitle = $(this).data('title');
	    		htmlListed += [
					'<div class="listed_item_parent B_listed_trade" data-code="' + listedCode + '" data-title="' + listedTitle + '">',
						'<a href="javascript:;" class="listed_item">',
							'<span>' + listedTitle + '</span><div class="del"></div>',
						'</a>',
					'</div>'
				].join('');
	    	});
	    	$('#B_listed_content').html(htmlListed);
	    	if ($('.B_listed_trade').length) {
	    		$('#B_listed_group').addClass('nmb');
	    	} else {
	    		$('#B_listed_group').removeClass('nmb');
	    	}
	    	$('#B_listed_group').show();
	    }

	    $('.B_listed_trade').on('click', function() {
	    	var listedValue = $(this).data('code');
	    	$('.B_list_trade').each(function(index, el) {
				if ($(this).data('code') == listedValue) {
					$(this).prop('checked', 0);
					$(this).closest('li').removeClass('current');
				};
			});
			copyTradeSelected();
	    });

	    // 清空
	    $('#B_listed_clear').on('click', function() {
	    	$('.B_list_trade:checked').each(function(index, el) {
				$(this).prop('checked', 0);
				$(this).closest('li').removeClass('current');
			});
			copyTradeSelected();
	    });

		// 确定
		$('#B_btnyes_trade').on('click', function(event) {
			var checkedArray = $('.B_list_trade:checked');
			var codeArray = new Array();
			var titleArray = new Array();
			$.each(checkedArray, function(index, val) {
				codeArray[index] = $(this).data('code');
				titleArray[index] = $(this).data('title');
			});
			$('#show_Trade .code_Trade').val(codeArray.join(','));
			;
			$('#show_Trade .title_Trade').text(titleArray.length ? titleArray.join('+') : '不限');
			$('#show_Trade .title_Trade').attr('title', titleArray.length ? titleArray.join('+') : '不限');
			
			var tit_Trade = $('#show_Trade .title_Trade').text();
			if(tit_Trade.length > 7){
                tit_Trade = tit_Trade.substring(0,7)+"...";
                $('#show_Trade .title_Trade').text(tit_Trade);
			}
			removeModal();
});
$('.B_dismiss_choice').on('click', function () {
    removeModal();
});
	});


	
    //其他功能
	function prepareModal(titleValue, multipleValue, maxNumValue) {
		var ie = !-[1,];
		var ie6 = !-[1,]&&!window.XMLHttpRequest;
		$(backdropLayerTpl).appendTo($(document.body));
		if (ie6) {
			$('.choice_backdrop').css("height", $(document).height());
		}
		$(htmlLayerTpl).appendTo($(document.body));

		$('.B_choice_title').text(titleValue);
		if (multipleValue) {
	    	$('.B_choice_max').text('（最多选择'+ maxNumValue +'个）');
	    };
	    if (!multipleValue) {
	    	$('.choice_footer').hide();
	    };

		$(".B_hoverbut").hover(
			function() {
				$(this).addClass("hover");
			},
			function() {
				$(this).removeClass("hover");
			}
		);

		// 可拖动
		var newObj = $('.choice_dialog');
		var newTit = newObj.find(".choice_header");

		newTit.mousedown(function(e) {
			var offset = newObj.offset();
			var x = e.pageX - offset.left;
            var y = e.pageY - offset.top;
            $(document).bind('mousemove', function(ev) {
            	newObj.bind('selectstart', function() {
                    return false;
                });
                var newx = ev.pageX - x;
                var newy = ev.pageY - y;
                newObj.css({
                    'left': newx + "px",
                    'top': newy + "px"
                });
            });
		});

		$(document).mouseup(function() {
            $(this).unbind("mousemove");
        })

		if (ie) {
			if (window.PIE) { 
	            $('.pie_about').each(function() {
	                PIE.attach(this);
	            });
	        }
		};
	}

	$('.B_dismiss_choice').on('click', function() {
        removeModal();
    });

    $(document).on('keydown', function(event) {
 		if (event.keyCode == 27) {
			removeModal();
		}
 	});

	function removeModal() {
		setTimeout(function() { 
	    	$('.choice_backdrop').remove();
 			$('.choice').remove();
		},50)
	}
	
}(window.jQuery);