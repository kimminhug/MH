        /* << 슬라이드 js >> */
        $(document).ready(function(){
            $(".slide").hide();                             /* 슬라이드할 이미지를 일단 모두 숨김 */
            $(".slide").first().fadeIn().show();            /* 첫번째 이미지만 일단 먼저 보이도록 함 */
            
            $(".slide").click(slide);                       /* 클릭하면 slide 함수 실행 */
            setInterval(slide, 5000);                       /* setInterval 메소드 : 일정시간(5초)마다 slide 함수가 실행 */
        });
        
        function slide(){                                   /* <슬라이드 함수> */
            $(".present").append($(".slide").first());      /* present 줄을 대상으로 처음 사진을 뒤에 추가함 */
            $(".slide").last().hide();                      /* 마지막 사진을 없엠 -> 두번째 사진이 첫번째 사진으로 설정됨 */
            $(".slide").first().fadeIn().show();            /* 페이드인 효과로 순서 바뀐 첫째 사진을 다시 보임 */
        }
        
        /* << 로그인창 js >> */
        function clearText(field){
            if (field.defaultValue == field.value){
                field.value = "";
            }
        }
        
        /* << 텍스트 스크롤 js >> */
        function rolling(options) {
			var self = this;
			this.object = document.getElementById(options.rollId);
			this.object.onmouseover = function() { self.stop(); };
			this.object.onmouseout = function() { self.play(); };
			this.delay = options.delay || 1000;
			this.speed = options.speed || 50;
			this.step = options.step || 1;
			this.mover = options.mover || false;
			this.elChildHeight = options.childHeight;
			this.elHeight = this.object.offsetHeight;
			this.elPosition = 0;
			this.object.appendChild(this.object.cloneNode(true));
			this.control = setTimeout(function() {self.play()}, this.delay);
		}
		rolling.prototype = {
			play:function() {
				var self = this, time;
				this.elPosition = this.elPosition>(this.mover?this.elHeight:0) ? this.elPosition-this.elHeight : this.elPosition+1;
				this.object.style.top = (this.mover ? -this.elPosition : this.elPosition) + "px";
				this.control = setTimeout(function() {self.play()}, this.elPosition%(this.elChildHeight*this.step)==0?this.delay:this.speed);
			},
			stop:function() {
				clearTimeout(this.control);
			}
		}
        
        /* << 메인창 처음 열릴시 js >> */
		window.onload = function main() {
			var roll =  new rolling({rollId: "rollText", delay: 2000, speed: 100, step: 1, mover: true, childHeight: 21});
            $("#m1").css("background-color", "cornflowerblue");
		}
        