  var _title,_source,_sourceUrl,_pic,_showcount,_desc,_summary,_site,
      _width = 600,
      _height = 600,
       _top = (screen.height-_height)/2,
       _left = (screen.width-_width)/2,
      _url = 'http://traveliceland.lofter.com/post/352b58_579d8e7',
       _pic = 'http://m3.img.srcdd.com/farm4/d/2015/0113/11/6AE3FEBE500857BF82CA97E8F03DD6A8_B500_900_500_411.jpeg';
  
    
  //��������΢��    
  function shareToSinaWB(event){
      event.preventDefault();
       
       var _shareUrl = 'http://v.t.sina.com.cn/share/share.php?&appkey=895033136';     //��ʵ��appkey����ѡ���� 
      _shareUrl += '&url='+ encodeURIComponent(_url||document.location);     //����url���÷������������|Ĭ�ϵ�ǰҳlocation����ѡ����
       _shareUrl += '&title=' + encodeURIComponent(_title||document.title);    //����title���÷���ı���|Ĭ�ϵ�ǰҳ���⣬��ѡ����
       _shareUrl += '&source=' + encodeURIComponent(_source||'');
      _shareUrl += '&sourceUrl=' + encodeURIComponent(_sourceUrl||'');
       _shareUrl += '&content=' + 'utf-8';   //����content����ҳ�����gb2312|utf-8����ѡ����
      _shareUrl += '&pic=' + encodeURIComponent(_pic||'');  //����pic����ͼƬ����|Ĭ��Ϊ�գ���ѡ����
      window.open(_shareUrl,'_blank','width='+_width+',height='+_height+',top='+_top+',left='+_left+',toolbar=no,menubar=no,scrollbars=no, resizable=1,location=no,status=0');
  }
  
  //����QQ�ռ�
  function shareToQzone(event){
      event.preventDefault();
      
      var _shareUrl = 'http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?';
     _shareUrl += 'url=' + encodeURIComponent(_url||document.location);   //����url���÷������������|Ĭ�ϵ�ǰҳlocation
      _shareUrl += '&showcount=' + _showcount||0;      //����showcount�Ƿ���ʾ��������,��ʾ��'1'������ʾ��'0'��Ĭ�ϲ���ʾ
     _shareUrl += '&desc=' + encodeURIComponent(_desc||'���������');    //����desc���÷������������ѡ����
      _shareUrl += '&summary=' + encodeURIComponent(_summary||'����ժҪ');    //����summary���÷���ժҪ����ѡ����
      _shareUrl += '&title=' + encodeURIComponent(_title||document.title);    //����title���÷�����⣬��ѡ����
      _shareUrl += '&site=' + encodeURIComponent(_site||'');   //����site���÷�����Դ����ѡ����
      _shareUrl += '&pics=' + encodeURIComponent(_pic||'');   //����pics���÷���ͼƬ��·��������ͼƬ�ԣ�|����������ѡ����
      window.open(_shareUrl,'_blank','width='+_width+',height='+_height+',top='+_top+',left='+_left+',toolbar=no,menubar=no,scrollbars=no,resizable=1,location=no,status=0');
  }
 
  //�����ٶ�����
  function shareToTieba(event){
     event.preventDefault();
     
     var _shareUrl = 'http://tieba.baidu.com/f/commit/share/openShareApi?';
      _shareUrl += 'title=' + encodeURIComponent(_title||document.title);  //����ı���
     _shareUrl += '&url=' + encodeURIComponent(_url||document.location);  //���������
      _shareUrl += '&pic=' + encodeURIComponent(_pic||'');    //�����ͼƬ
    window.open(_shareUrl,'_blank','width='+_width+',height='+_height+',left='+_left+',top='+_top+',toolbar=no,menubar=no,scrollbars=no,resizable=1,location=no,status=0');
 }
  
  //��������
   function shareToDouban(event){
      event.preventDefault();
 
      var _shareUrl = 'http://shuo.douban.com/!service/share?';
     _shareUrl += 'href=' + encodeURIComponent(_url||location.href);    //���������
      _shareUrl += '&name=' + encodeURIComponent(_title||document.title);    //����ı���
     _shareUrl += '&image=' + encodeURIComponent(_pic||'');    //�����ͼƬ
     window.open(_shareUrl,'_blank','width='+_width+',height='+_height+',left='+_left+',top='+_top+',toolbar=no,menubar=no,scrollbars=no,resizable=1,location=no,status=0');
  }
  
  //������Ѹ΢��
  function shareToQQwb(event){
      event.preventDefault();
     
      var _shareUrl = 'http://v.t.qq.com/share/share.php?';
      _shareUrl += 'title=' + encodeURIComponent(_title||document.title);    //����ı���
      _shareUrl += '&url=' + encodeURIComponent(_url||location.href);    //���������
      _shareUrl += '&appkey=5bd32d6f1dff4725ba40338b233ff155';    //����Ѹ΢��ƽ̨����Ӧ�û�ȡ΢��AppKey
     _shareUrl += '&site=' + encodeURIComponent(_site||'');   //������Դ
      _shareUrl += '&pic=' + encodeURIComponent(_pic||'');    //�����ͼƬ������Ƕ���ͼƬ������var _pic='ͼƬurl1|ͼƬurl2|ͼƬurl3....'
      window.open(_shareUrl,'_blank','width='+_width+',height='+_height+',left='+_left+',top='+_top+',toolbar=no,menubar=no,scrollbars=no,resizable=1,location=no,status=0');
 }
  
 //����������
  function shareToRenren(event){
      event.preventDefault();
      
      var _shareUrl = 'http://share.renren.com/share/buttonshare.do?';
     _shareUrl += 'link=' + encodeURIComponent(_url||location.href);   //���������
      _shareUrl += '&title=' + encodeURIComponent(_title||document.title);     //����ı���
      window.open(_shareUrl,'_blank','width='+_width+',height='+_height+',left='+_left+',top='+_top+',toolbar=no,menubar=no,scrollbars=no,resizable=1,location=no,status=0');
  }
  
  //����������
  function shareToKaixin(event){
      event.preventDefault();
      
      var _shareUrl = 'http://www.kaixin001.com/rest/records.php?';
      _shareUrl += 'url=' + encodeURIComponent(_url||location.href);    //���������
      _shareUrl += '&content=' + encodeURIComponent('���������');    //��Ҫ��������֣�������Ϊ��ʱ���Զ�ץȡ������ַ��title
      _shareUrl += '&pic=' + encodeURIComponent(_pic||'');     //�����ͼƬ,���ʹ�ð�Ƕ��ŷָ�
      _shareUrl += '&showcount=0';    //�Ƿ���ʾ����������ʾ��'1'������ʾ��'0'
      _shareUrl += '&style=11';      //��ʾ����ʽ����ѡ����
      _shareUrl += '&aid=' + encodeURIComponent(_site||'');    //��ʾ������Դ
     window.open(_shareUrl,'_blank','width='+_width+',height='+_height+',left='+_left+',top='+_top+',toolbar=no,menubar=no,scrollbars=no,resizable=1,location=no,status=0');
 }
  
  function shareToKaixin2(event){
      event.preventDefault();
     
      var _shareUrl = 'http://www.kaixin001.com/repaste/share.php?';
      _shareUrl += 'rtitle=' + encodeURIComponent(_title||document.title);   //����ı���
      window.open(_shareUrl,'_blank','width='+_width+',height='+_height+',left='+_left+',top='+_top+',toolbar=no,menubar=no,scrollbars=no,resizable=1,location=no,status=0');
  }
  
  //����facebook
  function shareToFacebook(event){
      event.preventDefault();
      
      var _shareUrl = 'http://www.facebook.com/sharer/sharer.php?';
      _shareUrl += 'u=' + encodeURIComponent(_url||location.href);    //���������
      _shareUrl += '&t=' + encodeURIComponent(_title||document.title);    //����ı���
      window.open(_shareUrl,'_blank','width='+_width+',height='+_height+',left='+_left+',top='+_top+',toolbar=no,menubar=no,scrollbars=no,resizable=1,location=no,status=0');
  }
  
 
  //����Twitter
  function shareToTwitter(event){
      event.preventDefault();
      
      var _shareUrl = 'http://twitter.com/intent/tweet?';
      _shareUrl += 'url=' + encodeURIComponent(_url||location.href);    //���������
      _shareUrl += '&text=' + encodeURIComponent(_title||document.title);    //����ı���
      window.open(_shareUrl,'_blank','width='+_width+',height='+_height+',left='+_left+',top='+_top+',toolbar=no,menubar=no,scrollbars=no,resizable=1,location=no,status=0');
  }