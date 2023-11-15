## Git

### Commit Convention

- [분류] 작업종류 : 작업내용
- 띄어쓰기 주의 !

```
// 분류
frontend : 프론트엔드
backend : 백엔드
infra : 인프라
common : 공통

// 작업 종류
Init : 초기화 (이건 쓸일 거의 없어여)
design: 스타일 수정 (css, UI 변경 등)
feat : 기능 개발
fix : 버그 수정
add : 단순 코드, 주석, 파일 추가
docs : readme 수정과 같은 문서
refactor : 리팩토링(코드, 성능) 각색
remove : 파일 삭제만 수행하는 경우
test : 테스팅 관련
chore : 위에 해당되지 않는 나머지 (기타 등등)

// 예시
feat(backend): implement user login api
```

<br>

### Branch Convention

```
<Master>
master = 당장 배포 가능한 수준 (FE + BE)

<Develop>
develop = 배포 전 통합 (FE + BE)
FE/develop = 배포 전 통합 (FE)
BE/develop = 배포 전 통합 (BE)

<Feature>
FE/[domain] = 개발 단계에서 도메인 별 통합 브랜치
```

<br>

### 참조문서

["How to Write Better Git Commit Messages - A Step-By-Step Guide", Natalie Pina](https://www.freecodecamp.org/news/how-to-write-better-git-commit-messages/)