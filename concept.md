# Neon Runner
## Minimal Cyberpunk Game Concept (1-Day Build)

### Core Concept
A simple Flappy Bird-style game where players control a small hover-bike navigating through the neon-lit urban canyon of a cyberpunk city. The bike must avoid obstacles while managing health and collecting power-ups.

### Setting & Atmosphere
The game takes place in a generic cyberpunk megacity with towering buildings creating a vertical maze. Simple geometric building shapes with neon accents create the classic cyberpunk aesthetic without complex art requirements.

**Visual Style (Simple Cyberpunk):**
- Dark gradient background (deep blue to black)
- Simple rectangular buildings with neon edge lighting (pink/cyan)
- Basic particle effects for sparks and rain
- Minimalist UI with cyberpunk color scheme (cyan/magenta)
- Simple geometric shapes with glowing outlines

### Gameplay Mechanics

**Core Movement:**
- Tap to boost upward (hover-bike thrust)
- Gravity pulls player downward
- Navigate between building gaps
- Horizontal auto-scroll through city

**Health System (Simplified):**
- Player starts with 3 health points (displayed as cyberpunk health bars)
- Taking damage removes 1 health and reduces bike speed slightly
- At 1 health: Bike moves noticeably slower, screen flickers occasionally
- At 0 health: Game over with simple crash effect

**Obstacles (Simple Cyberpunk):**
- **Building Gaps:** Main challenge - simple rectangular skyscrapers with neon outlines
- **Laser Barriers:** Horizontal/vertical glowing lines that deal damage
- **Floating Platforms:** Simple geometric platforms to navigate around

### Enemy Types (Simple Cyberpunk)

**Flying Enemies:**
- **Security Drones:** Small glowing rectangles with simple animations. Remove 1 health on contact
- **Hunter Bots:** Slightly larger, move slowly toward player. Remove 1 health on contact

**Ground-Based Threats:**
- **Turrets:** Simple geometric cannons on building ledges that fire slow glowing projectiles. Remove 1 health if hit

### Power-ups (2 Types Only)

**Rocket Boost:**
- **Appearance:** Simple thruster icon with cyan glow effect
- **Duration:** 5 seconds
- **Effect:** 
  - Increased speed and upward thrust
  - Immunity to damage
  - Cyan glow effect around bike
  - Simple particle trail
- **Spawn Rate:** Every 15-20 obstacles

**Data Pack (Health):**
- **Appearance:** Simple geometric shape with green/cyan glow
- **Effect:**
  - Restores 1 health (if not at max)
  - Brief screen flash effect
- **Spawn Rate:** Every 20-25 obstacles

### Scoring & Progression (Simple)

**Scoring System:**
- +1 point for each obstacle passed
- +5 points for collecting power-ups
- Simple high score tracking

**Difficulty Scaling (Minimal):**
- Obstacles spawn slightly faster over time
- Enemy spawn rate increases gradually

### Implementation Notes (1-Day Build)

**Essential Features Only:**
- Single tap controls
- 3-heart health system
- 2 power-up types
- 3 enemy types
- Basic collision detection
- Simple scoring
- Game over/restart functionality

**Art Assets (Minimal Cyberpunk):**
- Simple geometric shapes with neon outlines
- Cyberpunk color palette: cyan, magenta, purple, dark blue, white
- Glowing effects using simple CSS/canvas glow filters
- No complex animations - just position changes and simple rotations
- Dark gradient backgrounds with occasional "rain" lines

**Audio (Optional/Simple Cyberpunk):**
- 1 synthwave-style background track (if time permits)
- Basic electronic sound effects: boost, damage, power-up, game over
- Can use simple generated electronic tones with reverb

**Technical Implementation:**
- HTML5 Canvas with simple 2D rendering
- Basic collision detection (rectangular hit boxes)
- Simple particle effects (just moving colored rectangles)
- Cyberpunk UI styling with CSS glow effects
- Local storage for high score only

### Core Game Loop (Simplified)
1. Player taps to fly up, gravity pulls down
2. Avoid obstacles and enemies
3. Collect power-ups when possible
4. Try to beat high score
5. Game over when health reaches 0

**Estimated Development Time:** 4-8 hours for basic version