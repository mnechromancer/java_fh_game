# Neon Runner - Game Design Document

## Project Overview

**Genre:** Endless Runner / Obstacle Avoidance  
**Platform:** Web Browser (HTML5 Canvas)  
**Development Time:** 1 Day (4-8 hours)  
**Art Style:** Minimal Cyberpunk Aesthetic  
**Core Mechanic:** Tap-to-fly navigation through vertical urban canyon

## Core Gameplay Mechanics

### Primary Controls
- **Single Tap Input:** Tap/click to activate hover-bike thrust upward
- **Physics:** Constant gravity pulls player downward when not thrusting
- **Auto-Scroll:** Player moves horizontally through city at constant speed
- **Navigation:** Player controls vertical position to navigate between obstacles

### Health System
- **Starting Health:** 3 health points (visual: cyberpunk health bars)
- **Damage Response:** Each hit removes 1 health and reduces movement speed
- **Performance Degradation:** 
  - At 2 health: Normal performance
  - At 1 health: 15% speed reduction, occasional screen flicker
  - At 0 health: Game over with crash animation

### Power-Up System
**Rocket Boost (Offensive)**
- Duration: 5 seconds
- Effects: Increased thrust power, immunity to damage, cyan particle trail
- Visual: Thruster icon with cyan glow

**Data Pack (Defensive)**
- Effect: Restores 1 health point (if below maximum)
- Visual: Geometric shape with green/cyan pulse
- Feedback: Brief screen flash on collection

## Player Goals & Motivation

### Primary Objective
Navigate as far as possible through the cyberpunk city canyon while maintaining health and avoiding obstacles.

### Secondary Objectives
- **Score Maximization:** Earn points by passing obstacles (+1) and collecting power-ups (+5)
- **Survival Optimization:** Strategic power-up collection to maintain health
- **High Score Achievement:** Beat personal best distance/score

### Player Progression
- **Immediate Feedback:** Visual/audio response to all actions
- **Skill Development:** Improving timing and risk assessment
- **Challenge Scaling:** Gradual increase in obstacle density and enemy spawn rate

## Game Loop Definition

### Core Loop (15-30 seconds)
1. **Navigate** through 3-5 building gap obstacles
2. **Avoid** 1-2 enemy encounters (drones/turrets)
3. **Collect** 0-1 power-ups (if available)
4. **Assess** health status and risk tolerance
5. **Repeat** with slightly increased difficulty

### Meta Loop (2-5 minutes)
1. **Attempt** high score run
2. **Experience** game over scenario
3. **Review** score and compare to personal best
4. **Restart** with knowledge gained from previous run

### Session Loop (10-20 minutes)
1. **Multiple runs** with improving performance
2. **Skill mastery** of obstacle patterns
3. **Achievement** of satisfying high score
4. **Natural stopping point** when goal reached

## Asset Requirements

### Visual Assets (Minimal Cyberpunk)

**Environment**
- Dark gradient background (deep blue to black transition)
- Rectangular building silhouettes with neon edge lighting
- Simple rain particle effect (falling white lines)

**Player Character**
- Small geometric hover-bike (cyan/white color scheme)
- Thruster particle effect (cyan dots)
- Damage state visualization (red tint overlay)

**Obstacles & Enemies**
- Building gaps: Simple rectangles with pink/cyan neon outlines
- Laser barriers: Glowing horizontal/vertical lines (magenta)
- Security drones: Small rectangles with rotation animation
- Hunter bots: Larger rectangles with subtle movement toward player
- Turrets: Simple cannon shapes with muzzle flash effect

**UI Elements**
- Health bars: Cyberpunk-styled geometric shapes (cyan when full, red when low)
- Score display: Monospace font with neon glow effect
- Game over screen: Simple overlay with restart button

### Audio Assets (Optional)

**Sound Effects**
- Thrust boost: Electronic "whoosh" with reverb
- Damage hit: Sharp electronic "zap"
- Power-up collection: Rising electronic tone
- Game over: Descending synthesizer chord

**Music**
- Single synthwave background track (3-4 minute loop)
- Dark, atmospheric tone matching cyberpunk aesthetic

## Technical Constraints

### Platform Limitations
- **HTML5 Canvas rendering only**
- **No external libraries beyond basic JavaScript**
- **60fps target performance**
- **Responsive design for multiple screen sizes**

### Development Constraints
- **Maximum 8-hour development window**
- **Single developer workflow**
- **No complex art asset creation**
- **Minimal audio implementation**

### Performance Requirements
- **Smooth animation** at 60fps on mid-range devices
- **Instant input response** (< 16ms tap-to-action delay)
- **Efficient collision detection** using basic rectangular hit-boxes
- **Memory management** for particle effects and enemy spawning

## Success Criteria

### Technical Success Metrics
- **Playable build** completed within 8-hour timeframe
- **Core mechanics** (tap-to-fly, collision, health) fully functional
- **Stable performance** with no game-breaking bugs
- **Complete game loop** from start to game over to restart

### Design Success Metrics
- **Engaging core loop** that encourages replay
- **Clear player feedback** for all interactions
- **Balanced difficulty curve** that challenges without frustrating
- **Cohesive cyberpunk aesthetic** achieved with minimal assets

### Player Experience Goals
- **Easy to learn:** Player understands controls within 10 seconds
- **Difficult to master:** Skill ceiling allows for meaningful improvement
- **Satisfying feedback:** All actions provide clear visual/audio response
- **Replayable:** Players attempt multiple runs to improve scores

## Risk Mitigation

### Development Risks
- **Time overrun:** Focus on core mechanics first, polish later
- **Scope creep:** Strict adherence to minimal feature set
- **Technical complexity:** Use simplest possible implementations

### Design Risks
- **Repetitive gameplay:** Ensure obstacle variety within constraints
- **Frustrating difficulty:** Playtest frequently during development
- **Unclear feedback:** Prioritize player communication over visual polish

## Implementation Priority

### Phase 1 (Hours 1-3): Core Systems
1. Basic player movement and physics
2. Obstacle generation and collision detection
3. Health system and game over state

### Phase 2 (Hours 4-6): Content & Polish
1. Enemy types and behavior
2. Power-up system implementation
3. Scoring and UI systems

### Phase 3 (Hours 7-8): Final Polish
1. Visual effects and cyberpunk styling
2. Audio integration (if time permits)
3. Bug fixes and performance optimization

This design document provides a focused framework for creating an engaging, completable cyberpunk endless runner within the specified time constraints while maintaining clear success criteria and player experience goals.