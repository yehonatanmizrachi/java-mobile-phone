# Working with git:

## On the first time:
1) git clone https://github.com/yehonatanmizrachi/java-ex1.git
2) cd java-ex1

## Creating a new feature branch:
1) git checkout develop
2) git branch feature/XX
 

## Developing a feature:
1) git checkout feature/XX
2) Ctrl + S
3) git status (red)
4) git add .
5) git status (green)
6) git commit -m "commit message"
7) At the end of the day: git push OR git push origin feature/XX

## When the feature is completed:
1) git push OR git push origin feature/XX
2) Create PR on github
3) Confirm PR

## Pull develop updates to your current branch:
1) git checkout feature/XX
2) git pull origin develop

## Extra:
1) git pull - from github to the local repo
2) git log - view all the previous commits of the current branch.
