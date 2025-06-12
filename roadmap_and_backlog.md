# Neon Runner - Development Roadmap & Backlog

## Project Timeline Overview

**Total Development Time:** 8 hours (1 day)  
**Start Time:** 9:00 AM  
**Target Completion:** 5:00 PM  
**Buffer Time:** 30 minutes for unexpected issues

## Roadmap Phases

### Phase 1: Foundation (9:00 AM - 12:00 PM) - 3 Hours
**Goal:** Establish core game mechanics and playable prototype

### Phase 2: Content (12:00 PM - 3:00 PM) - 3 Hours  
**Goal:** Add enemies, power-ups, and scoring systems

### Phase 3: Polish (3:00 PM - 5:00 PM) - 2 Hours
**Goal:** Visual effects, audio, and final optimization

---

## Sprint Backlog (Prioritized by Risk & Dependencies)

### PHASE 1: FOUNDATION (Hours 1-3)

#### Sprint 1.1: Core Setup (9:00-9:30 AM) - 30 min
**Priority: CRITICAL - All other work depends on this**

| Task | Acceptance Criteria | Time Est. |
|------|-------------------|-----------|
| Create project structure | All .java files created with stub classes | 5 min |
| Setup NeonRunner main class | JFrame window displays (800x600) | 10 min |
| Create GamePanel with paint loop | Empty window with black background renders | 10 min |
| Implement Timer game loop | paintComponent called at 60fps | 5 min |

**Definition of Done:** Window opens, runs without errors, displays black screen

#### Sprint 1.2: Player Foundation (9:30-10:15 AM) - 45 min
**Priority: CRITICAL - Core gameplay mechanic**

| Task | Acceptance Criteria | Time Est. |
|------|-------------------|-----------|
| Create Player class structure | Player has x, y, velocityY properties | 10 min |
| Implement gravity physics | Player falls down screen continuously | 10 min |
| Add mouse click input | Click events detected and logged | 10 min |
| Connect input to thrust | Click makes player move upward | 10 min |
| Draw player rectangle | Cyan rectangle visible on screen | 5 min |

**Definition of Done:** Player rectangle responds to mouse clicks with upward movement

#### Sprint 1.3: Basic Obstacles (10:15-11:15 AM) - 60 min
**Priority: CRITICAL - Core challenge mechanic**

| Task | Acceptance Criteria | Time Est. |
|------|-------------------|-----------|
| Create Obstacle class | Basic rectangle obstacle with position | 15 min |
| Implement obstacle spawning | New obstacles appear off-screen right | 15 min |
| Add horizontal movement | Obstacles move left across screen | 10 min |
| Create obstacle cleanup | Off-screen obstacles removed from memory | 10 min |
| Basic collision detection | Rectangle.intersects() between player and obstacles | 10 min |

**Definition of Done:** Player can navigate between moving rectangular obstacles

#### Sprint 1.4: Health & Game Over (11:15 AM-12:00 PM) - 45 min
**Priority: HIGH - Essential game progression**

| Task | Acceptance Criteria | Time Est. |
|------|-------------------|-----------|
| Add health to Player | Player starts with 3 health points | 5 min |
| Implement damage system | Collision reduces health by 1 | 10 min |
| Create health display | 3 rectangles show current health | 10 min |
| Add game over state | Game stops when health reaches 0 | 10 min |
| Implement restart | Mouse click restarts game after game over | 10 min |

**Definition of Done:** Complete game loop - play until death, restart

---

### PHASE 2: CONTENT (Hours 4-6)

#### Sprint 2.1: Enemy Foundation (12:00-1:15 PM) - 75 min
**Priority: HIGH - Core content variety**

| Task | Acceptance Criteria | Time Est. |
|------|-------------------|-----------|
| Create Enemy base class | Abstract class with update/render methods | 15 min |
| Implement SecurityDrone | Moving rectangle with simple rotation | 20 min |
| Create HunterBot | Larger enemy that slowly tracks player Y position | 20 min |
| Add Turret class | Static enemy that fires simple projectiles | 20 min |

**Definition of Done:** Three distinct enemy types with unique behaviors

#### Sprint 2.2: Power-Up System (1:15-2:15 PM) - 60 min
**Priority: MEDIUM - Gameplay enhancement**

| Task | Acceptance Criteria | Time Est. |
|------|-------------------|-----------|
| Create PowerUp base class | PowerUp with type enum and position | 15 min |
| Implement RocketBoost | 5-second speed boost with invincibility | 20 min |
| Create DataPack | Restores 1 health point if below max | 15 min |
| Add collection logic | PowerUps disappear and apply effects on contact | 10 min |

**Definition of Done:** Two power-ups spawn, collect, and provide correct effects

#### Sprint 2.3: Scoring System (2:15-3:00 PM) - 45 min
**Priority: MEDIUM - Player motivation**

| Task | Acceptance Criteria | Time Est. |
|------|-------------------|-----------|
| Add score tracking | Score increments +1 for each obstacle passed | 10 min |
| Implement power-up bonuses | +5 points for each power-up collected | 5 min |
| Create score display | Score shown in top-left corner | 10 min |
| Add high score persistence | High score saved to/loaded from properties file | 20 min |

**Definition of Done:** Score tracks correctly and persists between sessions

---

### PHASE 3: POLISH (Hours 7-8)

#### Sprint 3.1: Visual Effects (3:00-3:45 PM) - 45 min
**Priority: MEDIUM - Player experience enhancement**

| Task | Acceptance Criteria | Time Est. |
|------|-------------------|-----------|
| Add gradient background | Blue-to-black cyberpunk gradient | 10 min |
| Implement neon glow effects | Stroke outlines on player and obstacles | 15 min |
| Create basic particle system | Cyan particles trail behind player thrust | 15 min |
| Add screen flash on damage | Brief red overlay when player takes damage | 5 min |

**Definition of Done:** Cyberpunk aesthetic with visual feedback

#### Sprint 3.2: Audio (Optional) (3:45-4:15 PM) - 30 min
**Priority: LOW - Nice to have**

| Task | Acceptance Criteria | Time Est. |
|------|-------------------|-----------|
| Create AudioManager class | Class to load and play sound clips | 10 min |
| Add thrust sound effect | Electronic sound plays on mouse click | 10 min |
| Implement damage sound | Sharp sound plays when taking damage | 5 min |
| Add power-up collection sound | Rising tone plays when collecting power-ups | 5 min |

**Definition of Done:** Basic audio feedback for key actions

#### Sprint 3.3: Final Polish (4:15-5:00 PM) - 45 min
**Priority: HIGH - Release preparation**

| Task | Acceptance Criteria | Time Est. |
|------|-------------------|-----------|
| Bug fixing and testing | No runtime exceptions, smooth gameplay | 20 min |
| Performance optimization | Consistent 60fps, object pooling where needed | 10 min |
| Difficulty balancing | Appropriate challenge curve for 2-5 minute sessions | 10 min |
| Final UI polish | Clean cyberpunk styling, readable fonts | 5 min |

**Definition of Done:** Polished, release-ready game

---

## Risk Mitigation & Contingency Plans

### High Risk Items (Monitor Closely)

#### Hour 2-3: Collision Detection
**Risk:** Complex collision logic taking too long  
**Mitigation:** Use simple Rectangle.intersects(), defer pixel-perfect collision  
**Contingency:** If behind schedule, simplify to basic bounds checking

#### Hour 4-5: Enemy AI
**Risk:** Enemy behaviors becoming too complex  
**Mitigation:** Keep AI simple - basic movement patterns only  
**Contingency:** Skip HunterBot tracking, make all enemies move in straight lines

#### Hour 6: File I/O for High Scores
**Risk:** Properties file handling causing issues  
**Mitigation:** Test file operations early, have fallback to in-memory only  
**Contingency:** Skip persistence, use session-only high score

### Schedule Buffer Strategy

#### If Ahead of Schedule:
- **30+ min ahead:** Add extra enemy type or power-up
- **15-30 min ahead:** Enhance visual effects with more particles
- **5-15 min ahead:** Polish UI and add more sound effects

#### If Behind Schedule:
- **15-30 min behind:** Skip audio implementation (Sprint 3.2)
- **30-60 min behind:** Skip advanced visual effects, use basic colors
- **60+ min behind:** Reduce enemies to 1 type, remove power-ups except health

---

## Definition of Done (Project Level)

### Minimum Viable Product (Must Have)
- [ ] Player controls hover-bike with mouse clicks
- [ ] Obstacles spawn and move across screen
- [ ] Collision detection works and damages player
- [ ] Health system with game over at 0 health
- [ ] Game can be restarted after game over
- [ ] Basic score tracking and display

### Enhanced Product (Should Have)
- [ ] At least 2 enemy types with different behaviors
- [ ] Both power-ups functional (boost and health)
- [ ] High score persistence
- [ ] Basic cyberpunk visual styling
- [ ] Performance maintains 60fps

### Polished Product (Nice to Have)
- [ ] All 3 enemy types implemented
- [ ] Particle effects and visual polish
- [ ] Audio feedback system
- [ ] Advanced neon glow effects
- [ ] Smooth difficulty progression

---

## Daily Standup Checkpoints

### 10:30 AM Checkpoint (After Sprint 1.2)
**Question:** "Is the player movement working correctly?"  
**Go/No-Go Decision:** If no, skip advanced physics and use simple movement

### 1:00 PM Checkpoint (After Sprint 2.1)  
**Question:** "Are basic enemies spawning and moving?"  
**Go/No-Go Decision:** If no, reduce scope to 1 enemy type

### 3:30 PM Checkpoint (After Sprint 3.1)
**Question:** "Is the core game fun and playable?"  
**Go/No-Go Decision:** If no, focus remaining time on bug fixes only

### 4:30 PM Final Checkpoint
**Question:** "Is the game ready for demonstration?"  
**Action:** Begin final testing and JAR packaging

---

## Success Metrics

### Technical Metrics
- **Compilation:** Clean compilation with no errors
- **Performance:** Maintains 50+ fps during gameplay
- **Stability:** No crashes during 5-minute play session

### Gameplay Metrics  
- **Learning Curve:** New player understands controls within 30 seconds
- **Engagement:** Average session length of 2+ minutes
- **Challenge:** Players can achieve score improvements across multiple attempts

### Aesthetic Metrics
- **Visual Coherence:** Consistent cyberpunk color scheme throughout
- **Feedback Quality:** Clear visual/audio response to all player actions
- **Polish Level:** No placeholder graphics or obvious development artifacts

This roadmap provides a structured, time-boxed approach to delivering a complete Neon Runner game within the 8-hour constraint while maintaining flexibility for scope adjustments based on progress.