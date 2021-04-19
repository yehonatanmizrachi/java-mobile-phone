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
7) At the end of the day: git push origin feature/XX OR git push (when you inside the branch)

## When the feature is completed:
1) add -> commit -> push
2) Create PR on github
3) Confirm PR (or wait for your friends CR)

## Pull develop updates to your current branch:
1) git checkout feature/XX
2) git pull origin develop
In case there is conflicts - solve them in VS code

## Remarks
1) For bugfixes or hotfixes open a branch: bugfix/XX, hotfix/XX
2) Don't work on the master branch!

## Extra:
1) git log - View all the previous commits of the current branch.
2) git diff - View all the changes compare to the last commit.
