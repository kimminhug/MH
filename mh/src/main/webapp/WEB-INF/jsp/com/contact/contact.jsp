<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="section-container no-padding">
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div id="map">
                	<iframe width="100%" height="300" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?width=100%25&amp;height=300&amp;hl=ko&amp;q=%EC%95%8C%ED%8C%8C%EC%8B%9C%ED%8B%B01%EB%A1%9C%20160+((%EC%A3%BC)%ED%94%8C%EB%9E%98%EC%8B%9C21)&amp;t=&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed"></iframe>
                </div>
            </div>
            <div class="col-xs-12">

                <div class="row">
                    <div class="col-md-6">
                        <form action="/contactSave.do" class="reveal-content contact-form" method="post">
                            <div class="form-group">
                                <input type="text" class="form-control" name="name" id="name" placeholder="Your name">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" name="email" id="email" placeholder="Email">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" name="title" id="title" placeholder="Subject">
                            </div>
                            <div class="form-group">
                                <textarea class="form-control" rows="3" name="content" id="content" placeholder="Enter your message"></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary btn-lg">Send</button>
                        </form>
                    </div>


                    <div class="col-md-5 col-md-offset-1">
                            
                        <h3>Office</h3>
                        
                        <div>
                            <p>대구 수성구 알파시티1로 160 <br/>Dnex(SW융합테크비즈센터) 306호</p>
                        </div>
                        <div>
                            <p>kmh@flash21.com<br>+82 010-2976-4393</p>
                        </div>
                    
                        <!-- <div>
                            <h3>Employment</h3>
                        </div>
                        <div>
                            <p>To apply for a job with our team, please feel free to send us your Linkedin profile link
                                ou CV to : employment@mybusiness.com</p>
                        </div> -->
                       
                    </div>
                </div>


            </div>

        </div>

    </div>
</div>