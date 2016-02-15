 /* ������������֮�� www.lanrenzhijia.com */
(function($,undefined){
	$.fn.zyUpload = function(options,param){
		var otherArgs = Array.prototype.slice.call(arguments, 1);
		if (typeof options == 'string') {
			var fn = this[0][options];
			if($.isFunction(fn)){
				return fn.apply(this, otherArgs);
			}else{
				throw ("zyUpload - No such method: " + options);
			}
		}

		return this.each(function(){
			var para = {};    // ��������
			var self = this;  // �����������
			
			var defaults = {
					width            : "700px",  					// ���
					height           : "400px",  					// ���
					itemWidth        : "140px",                     // �ļ���Ŀ��
					itemHeight       : "120px",                     // �ļ���ĸ߶�
					url              : "/upload/UploadAction",  	// �ϴ��ļ���·��
					multiple         : true,  						// �Ƿ���Զ���ļ��ϴ�
					dragDrop         : true,  						// �Ƿ�����϶��ϴ��ļ�
					del              : true,  						// �Ƿ����ɾ���ļ�
					finishDel        : false,  						// �Ƿ����ϴ��ļ���ɺ�ɾ��Ԥ��
					/* �ṩ���ⲿ�Ľӿڷ��� */
					onSelect         : function(selectFiles, files){},// ѡ���ļ��Ļص�����  selectFile:��ǰѡ�е��ļ�  allFiles:��û�ϴ���ȫ���ļ�
					onDelete		 : function(file, files){},     // ɾ��һ���ļ��Ļص����� file:��ǰɾ�����ļ�  files:ɾ��֮����ļ�
					onSuccess		 : function(file){},            // �ļ��ϴ��ɹ��Ļص�����
					onFailure		 : function(file){},            // �ļ��ϴ�ʧ�ܵĻص�����
					onComplete		 : function(responseInfo){},    // �ϴ���ɵĻص�����
			};
			
			para = $.extend(defaults,options);
			
			this.init = function(){
				this.createHtml();  // �������html
				this.createCorePlug();  // ���ú���js
			};
			
			/**
			 * ���ܣ������ϴ���ʹ�õ�html
			 * ����: ��
			 * ����: ��
			 */
			this.createHtml = function(){
				var multiple = "";  // ���ö�ѡ�Ĳ���
				para.multiple ? multiple = "multiple" : multiple = "";
				var html= '';
				
				if(para.dragDrop){
					// ���������϶���html
					html += '<form id="uploadForm" action="'+para.url+'" method="post" enctype="multipart/form-data">';
					html += '	<div class="upload_box">';
					html += '		<div class="upload_main">';
					html += '			<div class="upload_choose">';
	            	html += '				<div class="convent_choice">';
	            	html += '					<div class="andArea">';
	            	html += '						<div class="filePicker">���ѡ���ļ�</div>';
	            	html += '						<input id="fileImage" type="file" size="30" name="fileselect[]" '+multiple+'>';
	            	html += '					</div>';
	            	html += '				</div>';
					html += '				<span id="fileDragArea" class="upload_drag_area">���߽��ļ��ϵ��˴�</span>';
					html += '			</div>';
		            html += '			<div class="status_bar">';
		            html += '				<div id="status_info" class="info">ѡ��0���ļ�����0B��</div>';
		            html += '				<div class="btns">';
		            html += '					<div class="webuploader_pick">����ѡ��</div>';
		            html += '					<div class="upload_btn">��ʼ�ϴ�</div>';
		            html += '				</div>';
		            html += '			</div>';
					html += '			<div id="preview" class="upload_preview"></div>';
					html += '		</div>';
					html += '		<div class="upload_submit">';
					html += '			<button type="button" id="fileSubmit" class="upload_submit_btn">ȷ���ϴ��ļ�</button>';
					html += '		</div>';
					html += '		<div id="uploadInf" class="upload_inf"></div>';
					html += '	</div>';
					html += '</form>';
				}else{
					var imgWidth = parseInt(para.itemWidth.replace("px", ""))-15;
					
					// �����������϶���html
					html += '<form id="uploadForm" action="'+para.url+'" method="post" enctype="multipart/form-data">';
					html += '	<div class="upload_box">';
					html += '		<div class="upload_main single_main">';
		            html += '			<div class="status_bar">';
		            html += '				<div id="status_info" class="info">ѡ��0���ļ�����0B��</div>';
		            html += '				<div class="btns">';
		            html += '					<input id="fileImage" type="file" size="30" name="fileselect[]" '+multiple+'>';
		            html += '					<div class="webuploader_pick">ѡ���ļ�</div>';
		            html += '					<div class="upload_btn">��ʼ�ϴ�</div>';
		            html += '				</div>';
		            html += '			</div>';
		            html += '			<div id="preview" class="upload_preview">';
				    html += '				<div class="add_upload">';
				    html += '					<a style="height:'+para.itemHeight+';width:'+para.itemWidth+';" title="�������ļ�" id="rapidAddImg" class="add_imgBox" href="javascript:void(0)">';
				    html += '						<div class="uploadImg" style="width:'+imgWidth+'px">';
				    html += '							<img class="upload_image" src="control/images/add_img.png" style="width:expression(this.width > '+imgWidth+' ? '+imgWidth+'px : this.width)" />';
				    html += '						</div>';
				    html += '					</a>';
				    html += '				</div>';
					html += '			</div>';
					html += '		</div>';
					html += '		<div class="upload_submit">';
					html += '			<button type="button" id="fileSubmit" class="upload_submit_btn">ȷ���ϴ��ļ�</button>';
					html += '		</div>';
					html += '		<div id="uploadInf" class="upload_inf"></div>';
					html += '	</div>';
					html += '</form>';
				}
				
	            $(self).append(html).css({"width":para.width,"height":para.height});
	            
	            // ��ʼ��html֮��󶨰�ť�ĵ���¼�
	            this.addEvent();
			};
			
			/**
			 * ���ܣ���ʾͳ����Ϣ�Ͱ󶨼����ϴ����ϴ���ť�ĵ���¼�
			 * ����: ��
			 * ����: ��
			 */
			this.funSetStatusInfo = function(files){
				var size = 0;
				var num = files.length;
				$.each(files, function(k,v){
					// ����õ��ļ��ܴ�С
					size += v.size;
				});
				
				// ת��Ϊkb��MB��ʽ���ļ������֡���С�����Ͷ��ǿ�����ʵ������
				if (size > 1024 * 1024) {                    
					size = (Math.round(size * 100 / (1024 * 1024)) / 100).toString() + 'MB';                
				} else {                    
					size = (Math.round(size * 100 / 1024) / 100).toString() + 'KB';                
				}  
				
				// ��������
				$("#status_info").html("ѡ��"+num+"���ļ�����"+size+"��");
			};
			
			/**
			 * ���ܣ������ϴ����ļ���ʽ��
			 * ����: files ����ѡ����ļ�
			 * ����: ͨ�����ļ�
			 */
			this.funFilterEligibleFile = function(files){
				var arrFiles = [];  // �滻���ļ�����
				for (var i = 0, file; file = files[i]; i++) {
					if (file.size >= 51200000) {
						alert('�����"'+ file.name +'"�ļ���С����');	
					} else {
						// ��������Ҫ�жϵ�ǰ�����ļ���
						arrFiles.push(file);	
					}
				}
				return arrFiles;
			};
			
			/**
			 * ���ܣ� ��������͸�ʽ�ϵ�Ԥ��html
			 * ����: files ����ѡ����ļ�
			 * ����: Ԥ����html
			 */
			this.funDisposePreviewHtml = function(file, e){
				var html = "";
				var imgWidth = parseInt(para.itemWidth.replace("px", ""))-15;
				
				// �������ò���ɾ����ť
				var delHtml = "";
				if(para.del){  // ��ʾɾ����ť
					delHtml = '<span class="file_del" data-index="'+file.index+'" title="ɾ��"></span>';
				}
				
				// ����ͬ�����ļ������ͼ��
				var fileImgSrc = "control/images/fileType/";
				if(file.type.indexOf("rar") > 0){
					fileImgSrc = fileImgSrc + "rar.png";
				}else if(file.type.indexOf("zip") > 0){
					fileImgSrc = fileImgSrc + "zip.png";
				}else if(file.type.indexOf("text") > 0){
					fileImgSrc = fileImgSrc + "txt.png";
				}else{
					fileImgSrc = fileImgSrc + "file.png";
				}
				
				
				// ͼƬ�ϴ�����ͼƬ�������������ļ�
				if (file.type.indexOf("image") == 0) {
					html += '<div id="uploadList_'+ file.index +'" class="upload_append_list">';
					html += '	<div class="file_bar">';
					html += '		<div style="padding:5px;">';
					html += '			<p class="file_name">' + file.name + '</p>';
					html += delHtml;   // ɾ����ť��html
					html += '		</div>';
					html += '	</div>';
					html += '	<a style="height:'+para.itemHeight+';width:'+para.itemWidth+';" href="#" class="imgBox">';
					html += '		<div class="uploadImg" style="width:'+imgWidth+'px">';				
					html += '			<img id="uploadImage_'+file.index+'" class="upload_image" src="' + e.target.result + '" style="width:expression(this.width > '+imgWidth+' ? '+imgWidth+'px : this.width)" />';                                                                 
					html += '		</div>';
					html += '	</a>';
					html += '	<p id="uploadProgress_'+file.index+'" class="file_progress"></p>';
					html += '	<p id="uploadFailure_'+file.index+'" class="file_failure">�ϴ�ʧ�ܣ�������</p>';
					html += '	<p id="uploadSuccess_'+file.index+'" class="file_success"></p>';
					html += '</div>';
                	
				}else{
					html += '<div id="uploadList_'+ file.index +'" class="upload_append_list">';
					html += '	<div class="file_bar">';
					html += '		<div style="padding:5px;">';
					html += '			<p class="file_name">' + file.name + '</p>';
					html += delHtml;   // ɾ����ť��html
					html += '		</div>';
					html += '	</div>';
					html += '	<a style="height:'+para.itemHeight+';width:'+para.itemWidth+';" href="#" class="imgBox">';
					html += '		<div class="uploadImg" style="width:'+imgWidth+'px">';				
					html += '			<img id="uploadImage_'+file.index+'" class="upload_image" src="' + fileImgSrc + '" style="width:expression(this.width > '+imgWidth+' ? '+imgWidth+'px : this.width)" />';                                                                 
					html += '		</div>';
					html += '	</a>';
					html += '	<p id="uploadProgress_'+file.index+'" class="file_progress"></p>';
					html += '	<p id="uploadFailure_'+file.index+'" class="file_failure">�ϴ�ʧ�ܣ�������</p>';
					html += '	<p id="uploadSuccess_'+file.index+'" class="file_success"></p>';
					html += '</div>';
				}
				
				return html;
			};
			
			/**
			 * ���ܣ����ú��Ĳ��
			 * ����: ��
			 * ����: ��
			 */
			this.createCorePlug = function(){
				var params = {
					fileInput: $("#fileImage").get(0),
					uploadInput: $("#fileSubmit").get(0),
					dragDrop: $("#fileDragArea").get(0),
					url: $("#uploadForm").attr("action"),
					
					filterFile: function(files) {
						// ���˺ϸ���ļ�
						return self.funFilterEligibleFile(files);
					},
					onSelect: function(selectFiles, allFiles) {
						para.onSelect(selectFiles, allFiles);  // �ص�����
						self.funSetStatusInfo(ZYFILE.funReturnNeedFiles());  // ��ʾͳ����Ϣ
						var html = '', i = 0;
						// ��֯Ԥ��html
						var funDealtPreviewHtml = function() {
							file = selectFiles[i];
							if (file) {
								var reader = new FileReader()
								reader.onload = function(e) {
									// ���������ò����͸�ʽ��html
									html += self.funDisposePreviewHtml(file, e);
									
									i++;
									// �ٽ��ŵ��ô˷����ݹ���ɿ���Ԥ����html
									funDealtPreviewHtml();
								}
								reader.readAsDataURL(file);
							} else {
								// �ߵ�����˵���ļ�html�Ѿ���֯��ϣ�Ҫ��html��ӵ�Ԥ����
								funAppendPreviewHtml(html);
							}
						};
						
						// ���Ԥ��html
						var funAppendPreviewHtml = function(html){
							// ��ӵ���Ӱ�ťǰ
							if(para.dragDrop){
								$("#preview").append(html);
							}else{
								$(".add_upload").before(html);
							}
							// ��ɾ����ť
							funBindDelEvent();
							funBindHoverEvent();
						};
						
						// ��ɾ����ť�¼�
						var funBindDelEvent = function(){
							if($(".file_del").length>0){
								// ɾ������
								$(".file_del").click(function() {
									ZYFILE.funDeleteFile(parseInt($(this).attr("data-index")), true);
									return false;	
								});
							}
							
							if($(".file_edit").length>0){
								// �༭����
								$(".file_edit").click(function() {
									// ���ñ༭����
									//ZYFILE.funEditFile(parseInt($(this).attr("data-index")), true);
									return false;	
								});
							}
						};
						
						// ����ʾ�������¼�
						var funBindHoverEvent = function(){
							$(".upload_append_list").hover(
								function (e) {
									$(this).find(".file_bar").addClass("file_hover");
								},function (e) {
									$(this).find(".file_bar").removeClass("file_hover");
								}
							);
						};
						
						funDealtPreviewHtml();		
					},
					onDelete: function(file, files) {
						// �Ƴ�Ч��
						$("#uploadList_" + file.index).fadeOut();
						// ��������ͳ������Ϣ
						self.funSetStatusInfo(files);
						console.info("ʣ�µ��ļ�");
						console.info(files);
					},
					onProgress: function(file, loaded, total) {
						var eleProgress = $("#uploadProgress_" + file.index), percent = (loaded / total * 100).toFixed(2) + '%';
						if(eleProgress.is(":hidden")){
							eleProgress.show();
						}
						eleProgress.css("width",percent);
					},
					onSuccess: function(file, response) {
						$("#uploadProgress_" + file.index).hide();
						$("#uploadSuccess_" + file.index).show();
						$("#uploadInf").append("<p>�ϴ��ɹ����ļ���ַ�ǣ�" + response + "</p>");
						// �������ò���ȷ�����������ϴ��ɹ����ļ�
						if(para.finishDel){
							// �Ƴ�Ч��
							$("#uploadList_" + file.index).fadeOut();
							// ��������ͳ������Ϣ
							self.funSetStatusInfo(ZYFILE.funReturnNeedFiles());
						}
					},
					onFailure: function(file) {
						$("#uploadProgress_" + file.index).hide();
						$("#uploadSuccess_" + file.index).show();
						$("#uploadInf").append("<p>�ļ�" + file.name + "�ϴ�ʧ�ܣ�</p>");	
						//$("#uploadImage_" + file.index).css("opacity", 0.2);
					},
					onComplete: function(response){
						console.info(response);
					},
					onDragOver: function() {
						$(this).addClass("upload_drag_hover");
					},
					onDragLeave: function() {
						$(this).removeClass("upload_drag_hover");
					}

				};
				
				ZYFILE = $.extend(ZYFILE, params);
				ZYFILE.init();
			};
			
			/**
			 * ���ܣ����¼�
			 * ����: ��
			 * ����: ��
			 */
			this.addEvent = function(){
				// ����������ļ���ť����
				if($(".filePicker").length > 0){
					// ��ѡ���¼�
					$(".filePicker").bind("click", function(e){
		            	$("#fileImage").click();
		            });
				}
	            
				// �󶨼�����ӵ���¼�
				$(".webuploader_pick").bind("click", function(e){
	            	$("#fileImage").click();
	            });
				
				// ���ϴ�����¼�
				$(".upload_btn").bind("click", function(e){
					// �жϵ�ǰ�Ƿ����ļ���Ҫ�ϴ�
					if(ZYFILE.funReturnNeedFiles().length > 0){
						$("#fileSubmit").click();
					}else{
						alert("����ѡ���ļ��ٵ���ϴ�");
					}
	            });
				
				// ����������ļ���ť����
				if($("#rapidAddImg").length > 0){
					// ����ӵ���¼�
					$("#rapidAddImg").bind("click", function(e){
						$("#fileImage").click();
		            });
				}
			};
			
			
			// ��ʼ���ϴ����Ʋ���
			this.init();
		});
	};
})(jQuery);

