# ask-anything

### 백엔드 진행상황

##### 1주차 - 2주차

- spring cloud gateway에서 google id token 검증해서 security context에 principal 저장히기 (Oauth login)

##### 3주차

- Post 서비스 개발, Spring cloud gateway에서 post 서비스로 라우팅하는 로직 개발

##### 4주차

- Docker compose 를 활용해 gateway, post, Mysql DB를 패키징 함

##### 5주차

- Kubernetes와 minikube를 활용하여 클러스터를 구성하고 각각의 서비스들을 하나의 파드로 배포. k8s service를 활용해 외부에서 접근 가능한 endpoint 생성,

##### 6주차 - 7주차

- Test container 적용 R2dbc Container를 활용했고 init script를 통해서 schema와 test data 생성

### architecture overview

![ask_anything drawio (2)](https://user-images.githubusercontent.com/55564829/212544938-2adc86ad-50a1-45c3-ada1-8bd90f3abd5a.png)
