<?php
	include('initializeHome.php');
	include_once ('admin_global.php');
	session_start();
	if($_GET[action]=='logout')  $db->Get_user_out();
?>


<!DOCTYPE HTML>
<html>
	<head>
		<title>Learn It And Do It</title>
		<link href="./css/style.css" rel='stylesheet' type='text/css' />
		<meta http-equiv="Content鎼滅�?Type" content="text/html; charset=GBK">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="shortcut icon" type="image/x-icon" href="./images/fav-icon.png" />
		<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
		
		<!-- javascript-->
		<script src="js/pages.js" ></script>
		
		<!----webfonts---->
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
		<!----//webfonts---->
		<!-- Global CSS for the page and tiles -->
  		<link rel="stylesheet" href="./css/main.css">
  		<!-- //Global CSS for the page and tiles -->
		<!---start-click-drop-down-menu----->
		<script src="./js/jquery.min.js"></script>
        <!----start-dropdown--->
         <script type="text/javascript">
			var $ = jQuery.noConflict();
				$(function() {
					$('#activator').click(function(){
						$('#box').animate({'top':'0px'},500);
					});
					$('#boxclose').click(function(){
					$('#box').animate({'top':'-700px'},500);
					});
				});
				$(document).ready(function(){
				//Hide (Collapse) the toggle containers on load
				$(".toggle_container").hide(); 
				//Switch the "Open" and "Close" state per click then slide up/down (depending on open/close state)
				$(".trigger").click(function(){
					$(this).toggleClass("active").next().slideToggle("slow");
						return false; //Prevent the browser jump to the link anchor
				});					
			});
		</script>
        <!----//End-dropdown--->	
	</head>
	<body>
		<!---start-wrap---->
			<!---start-header---->
			<div class="header">
				<div class="wrap">
				<div class="logo">
					<a href="homePage.php"><img src="./images/logo.png" title="Learn It & Do It" /></a>
				</div>
				<div class="nav-icon">
					 <a href="#" class="right_bt" id="activator"><span> </span> </a>
				</div>
				 <div class="box" id="box">
					 <div class="box_content">        					                         
						<div class="box_content_center">
						 	<div class="form_content">
								<div class="menu_box_list">
									<ul>
										<li><a href="#"><span>home</span></a></li>
										<li><a href="#"><span>About</span></a></li>
										<li><a href="#"><span>Works</span></a></li>
										<li><a href="#"><span>Clients</span></a></li>
										<li><a href="#"><span>Blog</span></a></li>
										<li><a href="contact.html"><span>Contact</span></a></li>
										<div class="clear"> </div>
									</ul>
								</div>
								<a class="boxclose" id="boxclose"> <span> </span></a>
							</div>                                  
						</div> 	
					</div> 
				</div>       	  
				<div class="top-searchbar">
					<form action="searchPage.php" name="searchbar" method="post">
						<input type="text" name="keyword"/> 
						<input type="submit" name="submit" value="" />
					</form>
				</div>


				<div class="userinfo">
					<div >
					<ul id="navigation"> 
					<li onmouseover="displaySubMenu(this)" onmouseout="hideSubMenu(this)"> 
    				<?php 			
						if(isset($_SESSION['user_id'])) {
						$id=$_SESSION['user_id'];	
					?>
        			<li onmouseover="displaySubMenu(this)" onmouseout="hideSubMenu(this)"><a href="user/userSpace.php?id=<?php echo $id?>">
					<img style="vertical-align: middle;"  src="<?php  echo $_SESSION['user_pic_url'] ?>" title="user-name" />
					<span><?php echo $_SESSION['username'] ?></span></a>
                    <?php } else { ?>
						<img src="./images/user-pic.png" title="user-name" />
						<span>visitor</span></a>
					<?php } ?> 	
			</a>
            <ul>
               <li><a href="user/userSpace.php?id=<?php echo $id?>" id="my_level" rel="nofollow">Modify user info</a></li>
			   <li><a onClick="return confirm('Are You sure to log out?')"              
                href="homePage.php?action=logout"  rel="nofollow">    Log   Out    </a></li>
            </ul>
        </li>
    </ul>

					</div>
				</div>
				<div class="clear"> </div>
			</div>
		</div>
		
		<!---//End-header---->
		<!---start-content---->
		<div class="content">
			<div class="wrap">
			 <div id="main" role="main">
			    
			      <ul id="tiles">
			        <!-- These are our grid blocks -->
			      
			       <?php 
			        while ($row = mysql_fetch_array($result)) { ?> 
			        	<li>
			        	<a href='detailPage.php?article_id=<?php echo $row[2] ?>'>
			          <!-- <li onclick="location.href='detailPage.php?article_id=<?php echo $row[2] ?>";">  -->
			        	<img src= <?php echo $row[0] ?> width="200" height="200">
			         	<div class="post-info">
			       	<div class="post-basic-info">
				  		<h3><a href="#"><?php echo $row[1]  ?></a></h3>
				        	
				     		<p><?php echo $row[2] ?></p>
			       		</div>
			         		<div class="post-info-rate-share">
			        		<div class="rateit">
			        				<span> </span>
			         			</div>
			        			<div class="post-share">
			        				<span> </span>
			        			</div>
			        			<div class="clear"> </div>
			        		</div>
			        	</div>
			        	</a>
			        </li>
			        
			      <?php } ?>		      
				</ul>
			</div>
		  </div>
		</div>


			        
		<!---//End-content---->
		<!----wookmark-scripts---->
		  <script src="./js/jquery.imagesloaded.js"></script>
		  <script src="./js/jquery.wookmark.js"></script>
		  <script type="text/javascript">
		    (function ($){
		      var $tiles = $('#tiles'),
		          $handler = $('li', $tiles),
		          $main = $('#main'),
		          $window = $(window),
		          $document = $(document),
		          options = {
		            autoResize: true, // This will auto-update the layout when the browser window is resized.
		            container: $main, // Optional, used for some extra CSS styling
		            offset: 20, // Optional, the distance between grid items
		            itemWidth:280 // Optional, the width of a grid item
		          };
		      /**
		       * Reinitializes the wookmark handler after all images have loaded
		       */
		      function applyLayout() {
		        $tiles.imagesLoaded(function() {
		          // Destroy the old handler
		          if ($handler.wookmarkInstance) {
		            $handler.wookmarkInstance.clear();
		          }
		
		          // Create a new layout handler.
		          $handler = $('li', $tiles);
		          $handler.wookmark(options);
		        });
		      }
		      /**
		       * When scrolled all the way to the bottom, add more tiles
		      
		      function onScroll() {
		        // Check if we're within 100 pixels of the bottom edge of the broser window.
		        var winHeight = window.innerHeight ? window.innerHeight : $window.height(), // iphone fix
		            closeToBottom = ($window.scrollTop() + winHeight > $document.height() - 100);
		
		        if (closeToBottom) {
		          // Get the first then items from the grid, clone them, and add them to the bottom of the grid
		          var $items = $('li', $tiles),
		              $firstTen = $items.slice(0, 10);
		          $tiles.append($firstTen.clone());
		
		          applyLayout();
		        }
		      }; */
		
		      // Call the layout function for the first time
		      applyLayout();
		
		      // Capture scroll event.
		      $window.bind('scroll.wookmark', onScroll);
		    })(jQuery);
		  </script>
		<!----//wookmark-scripts---->
		<!----start-footer--->
		<div class="footer">
			<p> </p>
		</div>
		<!----//End-footer--->
		<!---//End-wrap---->
	</body>
</html>


