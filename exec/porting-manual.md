# 포팅 메뉴얼  

> 문서에서 현재 작업 디렉토리는 프로젝트의 최상위 폴더로 가정하고 서술한다.  

## 필요 환경  

프로젝트는 Helm으로 관리했기 때문에 프로젝트 구동을 위해서는 쿠버네티스 클러스터와 Helm이 필요하다. 본 문서에서는 로컬에서 Docker를 사용해 컨테이너로 쿠버네티스 개발 환경을 구성할 수 있는 kind를 사용하는 것을 기준으로 실행 방법을 서술한다.  

- Ubuntu (WSL2 가능)  
- [yq](https://github.com/mikefarah/yq)  
- [Docker](https://docs.docker.com/engine/install/)  
- [kind](https://github.com/kubernetes-sigs/kind)  
- [Helm](https://helm.sh/docs/intro/install/)  

## 실행  

kind를 설치했다면 클러스터를 생성해야 한다. 클러스터를 생성할 땐 Nginx Ingress 사용을 위한 추가 설정을 해주어야 한다.  

``` yaml
cat <<EOF >kind-cluster.yaml
kind: Cluster
apiVersion: kind.x-k8s.io/v1alpha4
name: dev-cluster
nodes:
- role: control-plane
  kubeadmConfigPatches:
  - |
    kind: InitConfiguration
    nodeRegistration:
      kubeletExtraArgs:
        node-labels: "ingress-ready=true"
  extraPortMappings:
  - containerPort: 80
    hostPort: 80
    protocol: TCP
  - containerPort: 443
    hostPort: 443
    protocol: TCP
- role: worker
- role: worker
EOF

kind create cluster --config kind-cluster.yaml
```

Nginx Ingress 사용을 위해 추가 과정이 필요하다. 해당 과정은 [이 문서](https://kind.sigs.k8s.io/docs/user/ingress/#ingress-nginx)를 참고하여 수행한다. kind 클러스터 구성이 끝났다면, 다음으로 모든 서비스의 컨테이너 이미지를 생성해야 한다. 이 과정은 `./workspace/bookulove-backend`에서 `bootBuildImage` gradle task를 실행하거나 아래의 스크립트를 실행하면 된다.  

``` text
./scripts/build-all-services.sh
```

생성된 컨테이너 이미지를 kind 클러스터에서 사용하기 위해서는 이를 import하는 과정이 필요하다. 아래 스크립트를 실행해 해당 작업을 수행할 수 있다.  

``` text
./scripts/import-all-services.sh [kind 클러스터 이름]
```

실제 서비스 배포 이전에 필수 환경 변수 설정 과정이 필요하다.  

- `global.jwt.secret`: JWT 생성 및 검증 과정에 사용될 임의의 값  
- `auth-service.coolSMS.sender`: CoolSMS 송신자 번호  
- `auth-service.coolSMS.apiKey`: CoolSMS API 키  
- `auth-service.coolSMS.apiSecretKey`: CoolSMS API 시크릿 키  
- `book-service.aladin.apiKey`: 알라딘 API 키  

위의 값들을 알맞은 값으로 변경한 뒤, 아래의 커맨드를 실행해 `prod-secret-values.yaml`을 생성한다.  

``` yaml
cd ./workspace/bookulove-chart

cat <<EOF >prod-secret-values.yaml
global:
  jwt:
    secret: &jwtSecret

auth-service:
  jwt:
    secret: *jwtSecret
  coolSMS:
    sender:
    apiKey:
    apiSecretKey:

book-service:
  aladin:
    apiKey:

chatting-service:
  jwt:
    secret: *jwtSecret

apigateway-service:
  jwt:
    secret: *jwtSecret
EOF
```

이제 아래 커맨드를 실행해 kind 클러스터에 서비스를 배포한다.  

``` text
helm install [-n KubernetesNamespace] ReleaseName . -f prod-secret-values.yaml
```
