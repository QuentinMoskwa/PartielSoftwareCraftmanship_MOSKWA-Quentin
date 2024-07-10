
using KataSurvivant.models;
using KataSurvivant.interfaces;
using KataSurvivant.services;


namespace KataSurvivant.Tests
{
    public class UnitTest1
    {
        [Test]
        public void TestRencontrerZombie()
        {
            var survivant = new Survivant(5, 5, "nord", 100);
            var zombie = new Zombie(5, 5);
            IExplorationService explorationService = new ExplorationService();
            explorationService.RencontrerZombie(survivant, zombie);
            Assert.Equal(90, survivant.Sante);
        }

        [Test]
        public void TestRamasserRessource()
        {
            var survivant = new Survivant(5, 5, "nord", 100);
            var ressource = new Ressource("eau", 5, 5);
            var carte = new Carte(10, 10);
            carte.AjouterRessource(ressource);
            IExplorationService explorationService = new ExplorationService();
            explorationService.Explorer(survivant, carte, "avancer");
            Assert.Equal(1, survivant.Inventaire.Count);
            Assert.Equal("eau", survivant.Inventaire[0].Type);
        }
    }
}

}