# FGW Test Automation Pipeline

## 🔭 Overview

This project contains the CI/CD pipeline for FGW Test Automation.

The pipeline is designed with a **decoupled architecture**, where:
- Build happens only on master merge
- Test execution happens separately
- Artifacts are reused from Nexus

---

## ⚡ Execution Modes

### 1. Merge to Master
- preparation → build → upload
- JAR uploaded to Nexus
- No test execution

### 2. Manual Master Run
- run → notify
- JAR downloaded from Nexus

### 3. Feature Branch Run
- build → run → notify
- JAR built inside pipeline

---

## 🔗 Dev Integration

- Dev pipeline triggers test pipeline automatically
- Runs sanity tests after deployment
- Sends results to Dev + Test teams

---

## 🧱 Stages

- preparation
- build
- upload
- run
- notify

---

## 📧 Notifications

- Master → Dev + Test team
- Feature → Dev team

---

## ⚙️ Variables

| Variable | Description |
|----------|------------|
| EXECUTION_MODE | DEV_TRIGGER / WEB / SCHEDULE |
| JARArgument1 | Workflow selection |

---

## 🧪 Workflows

- C2B
- E2E
- B2B
- SAM
- UIValidation

---

## ▶️ How to Run

### Feature
1. Run pipeline
2. Select branch
3. Provide JARArgument1

### Master
1. Run pipeline
2. Select master
3. Execution starts using Nexus JAR

---

## 📊 Benefits

- Faster execution
- No redundant builds
- Artifact reuse
- Better automation

---

## 📌 Notes

- Feature push does not trigger pipeline
- Build is skipped for master manual run
